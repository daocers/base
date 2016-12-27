package co.bugu.wechat.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.wechat.model.common.UserInfo;
import co.bugu.wechat.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(UserInfo userinfo) {
        return baseDao.insert("wechat.userinfo.insert", userinfo);
    }

    @Override
    public int updateById(UserInfo userinfo) {
        return baseDao.update("wechat.userinfo.updateById", userinfo);
    }

    @Override
    public int saveOrUpdate(UserInfo userinfo) {
        if(userinfo.getUnionid() == null){
            return baseDao.insert("wechat.userinfo.insert", userinfo);
        }else{
            return baseDao.update("wechat.userinfo.updateById", userinfo);
        }
    }

    @Override
    public int delete(UserInfo userinfo) {
        return baseDao.delete("wechat.userinfo.deleteById", userinfo);
    }

    @Override
    public UserInfo findById(String unionid) {
        return baseDao.selectOne("wechat.userinfo.selectById", unionid);
    }

    @Override
    public List<UserInfo> findAllByObject(UserInfo userinfo) {
        return baseDao.selectList("wechat.userinfo.listByObject", userinfo);
    }


    @Override
    public PageInfo listByObject(UserInfo userinfo, PageInfo<UserInfo> pageInfo) throws Exception {
        return baseDao.listByObject("wechat.userinfo.listByObject", userinfo, pageInfo);
    }
}
