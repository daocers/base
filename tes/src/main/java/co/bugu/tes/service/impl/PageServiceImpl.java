package co.bugu.tes.service.impl;


import co.bugu.tes.model.Page;
import co.bugu.tes.service.IPageService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements IPageService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Page page) {
        return baseDao.insert("tes.page.insert", page);
    }

    @Override
    public int updateById(Page page) {
        return baseDao.update("tes.page.updateById", page);
    }

    @Override
    public int saveOrUpdate(Page page) {
        if(page.getId() == null){
            return baseDao.insert("tes.page.insert", page);
        }else{
            return baseDao.update("tes.page.updateById", page);
        }
    }

    @Override
    public int delete(Page page) {
        return baseDao.delete("tes.page.deleteById", page);
    }

    @Override
    public Page findById(Integer id) {
        return baseDao.selectOne("tes.page.selectById", id);
    }

    @Override
    public List<Page> findAllByObject(Page page) {
        return baseDao.selectList("tes.page.listByObject", page);
    }

    @Override
    public PageInfo listByObject(Page page, PageInfo<Page> pageInfo) throws Exception {
        return baseDao.listByObject("tes.page.listByObject", page, pageInfo);
    }
}
