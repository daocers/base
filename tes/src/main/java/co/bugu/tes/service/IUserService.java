package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.User;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IUserService extends IBaseService<User>{
//    int save(User user);
//
//    int updateById(User user);
//
//    int saveOrUpdate(User user);
//
//    int delete(User user);
//
//    User findById(Integer id);
//
//    List<User> findAllByObject(User user);
//
//    PageInfo listByObject(User user, PageInfo<User> pageInfo) throws Exception;

    void batchAdd(List<User> userList);
}
