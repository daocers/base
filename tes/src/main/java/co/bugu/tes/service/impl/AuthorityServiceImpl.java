package co.bugu.tes.service.impl;


import co.bugu.tes.model.Authority;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Authority authority) {
        return baseDao.insert("tes.authority.insert", authority);
    }

    @Override
    public int updateById(Authority authority) {
        return baseDao.update("tes.authority.updateById", authority);
    }

    @Override
    public int saveOrUpdate(Authority authority) {
        if(authority.getId() == null){
            return baseDao.insert("tes.authority.insert", authority);
        }else{
            return baseDao.update("tes.authority.updateById", authority);
        }
    }

    @Override
    public int delete(Authority authority) {
        return baseDao.delete("tes.authority.deleteById", authority);
    }

    @Override
    public void batchUpdate(Authority authority) {
        baseDao.update("tes.authority.batchUpdate", authority);
    }

    @Override
    public Authority findById(Integer id) {
        return baseDao.selectOne("tes.authority.selectById", id);
    }

    @Override
    public List<Authority> findAllByObject(Authority authority) {
        return baseDao.selectList("tes.authority.listByObject", authority);
    }

    @Override
    public PageInfo listByObject(Authority authority, PageInfo<Authority> pageInfo) throws Exception {
        return baseDao.listByObject("tes.authority.listByObject", authority, pageInfo);
    }

    @Override
    public void rebuildInfo(List<Authority> authorityList) {
        for(Authority authority: authorityList){
            if(authority.getId() == null){
                baseDao.insert("tes.authority.insert", authority);
            }else{
                baseDao.update("tes.authority.updateById", authority);
            }
        }
    }
}
