package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.framework.util.JedisUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Profile;
import co.bugu.tes.model.Role;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    //    @Autowired
//    BaseDao baseDao;
//
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

    //
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

    //
//    @Override
//    public int saveOrUpdate(User user) {
//        if(user.getId() == null){
//            return baseDao.insert("tes.user.insert", user);
//        }else{
//            return baseDao.update("tes.user.updateById", user);
//        }
//    }
//
//    @Override
//    public int delete(User user) {
//        return baseDao.delete("tes.user.deleteById", user);
//    }
//
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
                for(Role role: roles){
                    List<Authority> authorities = baseDao.selectList("tes.authority.selectAuthorityByRole", role.getId());
                    if(authorities != null){
                        role.setAuthorityList(authorities);
                    }
                }
            }
        }
        return user;
    }
}
