package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Authority;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements IAuthorityService {
    @Autowired
    BaseDao<Authority> baseDao;

//    public int saveOrUpdate(Authority authority) {
//        if(authority.getId() == null){
//            return baseDao.insert("tes.authority.insert", authority);
//        }else{
//            return baseDao.update("tes.authority.updateById", authority);
//        }
//    }


    public void rebuildInfo(List<Authority> authorityList) {
        for(Authority authority: authorityList){
            if(authority.getId() == null){
                baseDao.insert("tes.authority.insert", authority);
            }else{
                baseDao.update("tes.authority.updateById", authority);
            }
        }
    }

    @Override
    public List<String> getAllController() {
        return baseDao.selectList("tes.authority.getAllController");
    }

    @Override
    public void batchUpdate(Authority authority) {

    }
}
