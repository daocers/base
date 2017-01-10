package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
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
//    @Override
//    public int save(User user) {
//        return baseDao.insert("tes.user.insert", user);
//    }
//
//    @Override
//    public int updateById(User user) {
//        return baseDao.update("tes.user.updateById", user);
//    }
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
