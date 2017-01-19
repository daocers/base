package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Profile;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
//    @Autowired
//    BaseDao baseDao;
//
    @Override
    public int save(User user) {
        baseDao.insert("tes.user.insert", user);
        if(user.getProfile() != null){
            user.getProfile().setUserId(user.getId());
            baseDao.insert("tes.profile.insert", user.getProfile());
        }
        return 1;
    }
//
    @Override
    public int updateById(User user) {
        baseDao.update("tes.user.updateById", user);
        if(user.getProfile() != null){
            Profile profile = user.getProfile();
            profile.setUserId(user.getId());
            if(profile.getId() == null){
                baseDao.insert("tes.profile.insert", profile);
            }else{
                baseDao.update("tes.profile.updateById", profile);
            }
        }
        return 1;
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
//    @Override
//    public User findById(Integer id) {
//        return baseDao.selectOne("tes.user.selectById", id);
//    }
//
//    @Override
//    public List<User> findAllByObject(User user) {
//        return baseDao.selectList("tes.user.listByObject", user);
//    }
//
//    @Override
//    public PageInfo listByObject(User user, PageInfo<User> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.user.listByObject", user, pageInfo);
//    }
}
