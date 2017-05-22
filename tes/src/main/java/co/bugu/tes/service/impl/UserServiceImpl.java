package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
//import co.bugu.framework.util.JedisUtil;
import co.bugu.framework.core.util.JedisUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Profile;
import co.bugu.tes.model.Role;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public int save(User user) {
        baseDao.insert("tes.user.insert", user);
        if (user.getProfile() != null) {
            user.getProfile().setUserId(user.getId());
            baseDao.insert("tes.profile.insert", user.getProfile());
        }
        JedisUtil.setJson(user);
//        JedisUtil.setJson(Constant.USER_INFO_PREFIX + user.getId(), user);
        return 1;
    }

    @Override
    public int delete(User record) {
        JedisUtil.delObj(record);
        Profile profile = new Profile();
        profile.setUserId(record.getId());
        List<Profile> profiles = baseDao.selectList("tes.profile.findByObject", profile);
        if(CollectionUtils.isNotEmpty(profiles)){
            for(Profile p: profiles){
                baseDao.delete("tes.profile.deleteById", p);
            }
        }
        return baseDao.delete("tes.user.deleteById", record);
    }


    @Override
    public int updateById(User user) {
        baseDao.update("tes.user.updateById", user);
        if (user.getProfile() != null) {
            Profile profile = user.getProfile();
            profile.setUserId(user.getId());
            if (profile.getId() == null) {
                baseDao.insert("tes.profile.insert", profile);
            } else {
                baseDao.update("tes.profile.updateById", profile);
            }
        }
        JedisUtil.setJson(user);
        return 1;
    }

    @Override
    public void batchAdd(List<User> userList) {
        for(User user: userList){
            baseDao.insert("tes.user.insert", user);
            Profile profile = user.getProfile();
            profile.setName(user.getName());
            profile.setUserId(user.getId());
            profile.setRegistTime(new Date());
            baseDao.insert("tes.profile.insert", profile);
        }

    }

    @Override
    public User findById(Integer id) {
        try{
            User user = JedisUtil.getJson(getKeyFromClassAndId(id, User.class), User.class);
            if(user != null){
                return user;
            }
        }catch (Exception e){
            logger.error("查询redis失败", e);
        }
        /**
         * 由于此处查询数据不全，所以不做缓存更新
         * */
        return baseDao.selectOne("tes.user.selectById", id);
    }

    @Override
    public User findFullById(Integer id) {
        try{
            User user = JedisUtil.getJson(getKeyFromClassAndId(id, User.class), User.class);
            if(user != null){
                return user;
            }
        }catch (Exception e){
            logger.error("查询redis失败",e);
        }
        User user = baseDao.selectOne("tes.user.selectById", id);
        if(user != null){
            List<Role> roles = baseDao.selectList("tes.role.selectRoleByUser", user.getId());
            if(CollectionUtils.isNotEmpty(roles)){
                user.setRoleList(roles);
                List<String> roleList = new ArrayList<>();
                List<String> authList = new ArrayList<>();
                for(Role role: roles){
                    roleList.add(role.getCode());
                    List<Authority> authorities = baseDao.selectList("tes.authority.selectAuthorityByRole", role.getId());
                    if(authorities != null){
                        role.setAuthorityList(authorities);
                        for(Authority authority: authorities){
                            authList.add(authority.getId() + "");
                        }
                    }
                }
                JedisUtil.rPush(Constant.USER_ROLES + id, roleList.toArray(new String[roleList.size()]));
                JedisUtil.rPush(Constant.USER_AUTHORITYS + id, authList.toArray(new String[authList.size()]));
            }else {
                user.setRoleList(new ArrayList<>());
            }
        }
        JedisUtil.setJson(user);
        return user;
    }

    @Override
    public List<String> getRoleList(Integer userId) {
        List<String> roleList = new ArrayList<>();
        try{
            roleList = JedisUtil.getAllList(Constant.USER_ROLES + userId);
        }catch (Exception e){
            logger.error("jedis获取列表失败");

        }
        if(CollectionUtils.isEmpty(roleList)){
            roleList = new ArrayList<>();
            List<Role> list = baseDao.selectList("tes.role.selectRoleByUser", userId);
            for(Role role: list){
                roleList.add(role.getCode());
            }
            JedisUtil.rPush(Constant.USER_ROLES + userId, roleList.toArray(new String[roleList.size()]));
        }
        return roleList;
    }

    @Override
    public List<String> getAuthorityList(Integer userId) {
        List<String> authorityList = new ArrayList<>();
        try{
            authorityList = JedisUtil.getAllList(Constant.USER_AUTHORITYS + userId);
        }catch (Exception e){
            logger.error("jedis获取列表失败");

        }
        if(CollectionUtils.isEmpty(authorityList)){
            authorityList = new ArrayList<>();
            List<Role> list = baseDao.selectList("tes.role.selectRoleByUser", userId);
            for(Role role: list){
                List<Authority> authorities = baseDao.selectList("tes.authority.selectAuthorityByRole", role.getId());
                for(Authority authority: authorities){
                    authorityList.add(authority.getId() +"");
                }
            }
            JedisUtil.rPush(Constant.USER_AUTHORITYS + userId, authorityList.toArray(new String[authorityList.size()]));
//            JedisUtil.pushList(Constant.USER_AUTHORITYS + userId, authorityList);
        }
        return authorityList;
    }

    @Override
    public boolean hasRole(Integer userId, String... role) {
        return getRoleList(userId).containsAll(Arrays.asList(role));
    }

    @Override
    public boolean hasAuthority(Integer userId, String... authority) {
        return getAuthorityList(userId).containsAll(Arrays.asList(authority));
    }

    @Override
    public boolean updateRole(Integer userId, List<Integer> rolesNew) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);

        User user = findFullById(userId);
        List<Role> hasList = user.getRoleList();
        Iterator<Role> iterator = hasList.iterator();
        while(iterator.hasNext()){
            Role role = iterator.next();
            Integer id = role.getId();
            if(rolesNew.contains(id)){//已经拥有的角色
                iterator.remove();
                rolesNew.remove(id);
            }
        }
        /**
         * 处理新增加的角色
         * */
        for(Integer roleId: rolesNew){
            map.put("roleId", roleId);
            baseDao.insert("tes.user.addUserRoleX", map);
        }

//        处理不再拥有的角色
        for(Role role: hasList){
            map.put("roleId", role.getId());
            baseDao.delete("tes.user.deleteUserRoleX", map);
        }

//        清空缓存
        JedisUtil.delObj(user);
        return true;
    }
}
