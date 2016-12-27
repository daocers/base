package co.bugu.tes.service.impl;


import co.bugu.tes.model.Parent;
import co.bugu.tes.service.IParentService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImpl implements IParentService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Parent parent) {
        return baseDao.insert("tes.parent.insert", parent);
    }

    @Override
    public int updateById(Parent parent) {
        return baseDao.update("tes.parent.updateById", parent);
    }

    @Override
    public int saveOrUpdate(Parent parent) {
        if(parent.getId() == null){
            return baseDao.insert("tes.parent.insert", parent);
        }else{
            return baseDao.update("tes.parent.updateById", parent);
        }
    }

    @Override
    public int delete(Parent parent) {
        return baseDao.delete("tes.parent.deleteById", parent);
    }

    @Override
    public Parent findById(Integer id) {
        return baseDao.selectOne("tes.parent.selectById", id);
    }

    @Override
    public List<Parent> findAllByObject(Parent parent) {
        return baseDao.selectList("tes.parent.listByObject", parent);
    }

    @Override
    public PageInfo listByObject(Parent parent, PageInfo<Parent> pageInfo) throws Exception {
        return baseDao.listByObject("tes.parent.listByObject", parent, pageInfo);
    }
}
