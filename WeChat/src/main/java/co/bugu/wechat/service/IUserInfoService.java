package co.bugu.wechat.service;


import co.bugu.framework.core.dao.PageInfo;
import co.bugu.wechat.model.common.UserInfo;

import java.util.List;

public interface IUserInfoService {
    int save(UserInfo userinfo);

    int updateById(UserInfo userinfo);

    int saveOrUpdate(UserInfo userinfo);

    int delete(UserInfo userinfo);

    UserInfo findById(String unionid);

    List<UserInfo> findAllByObject(UserInfo userinfo);

    PageInfo listByObject(UserInfo userinfo, PageInfo<UserInfo> pageInfo) throws Exception;

}
