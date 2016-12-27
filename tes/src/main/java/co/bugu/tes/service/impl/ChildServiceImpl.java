package co.bugu.tes.service.impl;


import co.bugu.tes.model.Child;
import co.bugu.tes.service.IChildService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements IChildService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Child child) {
        return baseDao.insert("tes.child.insert", child);
    }

    @Override
    public int updateById(Child child) {
        return baseDao.update("tes.child.updateById", child);
    }

    @Override
    public int saveOrUpdate(Child child) {
        if(child.getId() == null){
            return baseDao.insert("tes.child.insert", child);
        }else{
            return baseDao.update("tes.child.updateById", child);
        }
    }

    @Override
    public int delete(Child child) {
        return baseDao.delete("tes.child.deleteById", child);
    }

    @Override
    public Child findById(Integer id) {
        return baseDao.selectOne("tes.child.selectById", id);
    }

    @Override
    public List<Child> findAllByObject(Child child) {
        return baseDao.selectList("tes.child.listByObject", child);
    }

    @Override
    public PageInfo listByObject(Child child, PageInfo<Child> pageInfo) throws Exception {
        return baseDao.listByObject("tes.child.listByObject", child, pageInfo);
    }
}
