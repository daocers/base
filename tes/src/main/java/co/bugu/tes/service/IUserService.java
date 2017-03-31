package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.User;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IUserService extends IBaseService<User> {

    void batchAdd(List<User> userList);

    User findFullById(Integer id);

    List<String> getRoleList(Integer userId);

    List<String> getAuthorityList(Integer userId);

}
