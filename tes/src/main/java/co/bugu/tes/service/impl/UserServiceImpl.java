package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.framework.util.JedisUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Profile;
import co.bugu.tes.model.Role;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.tools.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        JedisUtil.setJson(Constant.USER_INFO_PREFIX + user.getId(), user);
        return 1;
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
        JedisUtil.setJson(Constant.USER_INFO_PREFIX + user.getId(), user);
        return 1;
    }

    @Override
    public void batchAdd(List<User> userList) {

    }

    @Override
    public User findById(Integer id) {;
        try{
            User user = JedisUtil.getJson(Constant.USER_INFO_PREFIX + id, User.class);
            return user;
        }catch (Exception e){
            logger.error("查询redis失败", e);
        }
        return baseDao.selectOne("tes.user.selectById", id);
    }

    @Override
    public User findFullById(Integer id) {
        try{
            User user = JedisUtil.getJson(Constant.USER_INFO_PREFIX + id, User.class);
            if(user != null){
                return user;
            }
        }catch (Exception e){
            logger.error("查询redis失败",e);
        }
        User user = baseDao.selectOne("tes.user.selectById", id);
        if(user != null){
            List<Role> roles = baseDao.selectList("tes.role.selectRoleByUser", user.getId());
            if(roles != null && roles.size() > 0){
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
                JedisUtil.pushList(Constant.USER_ROLES + id, roleList);
                JedisUtil.pushList(Constant.USER_AUTHORITYS + id, authList);
            }
        }
        JedisUtil.setJson(Constant.USER_INFO_PREFIX + id, user);
        return user;
    }

    @Override
    public List<String> getRoleList(Integer userId) {
        List<String> roleList = new ArrayList<>();
        try{
            roleList = JedisUtil.lrange(Constant.USER_ROLES + userId);
        }catch (Exception e){
            logger.error("jedis获取列表失败");

        }
        if(roleList == null || roleList.size() == 0){
            List<Role> list = baseDao.selectList("tes.role.selectRoleByUser", userId);
            for(Role role: list){
                roleList.add(role.getCode());
            }
            JedisUtil.pushList(Constant.USER_ROLES + userId, roleList);
        }
        return roleList;
    }

    @Override
    public List<String> getAuthorityList(Integer userId) {
        List<String> authorityList = new ArrayList<>();
        try{
            authorityList = JedisUtil.lrange(Constant.USER_AUTHORITYS + userId);
        }catch (Exception e){
            logger.error("jedis获取列表失败");
            List<Role> list = baseDao.selectList("tes.role.selectRoleByUser", userId);
            for(Role role: list){
                List<Authority> authorities = baseDao.selectList("tes.authority.selectAuthorityByRole", role.getId());
                for(Authority authority: authorities){
                    authorityList.add(authority.getId() +"");
                }
            }
            JedisUtil.pushList(Constant.USER_AUTHORITYS + userId, authorityList);
        }
        return authorityList;
    }
}
