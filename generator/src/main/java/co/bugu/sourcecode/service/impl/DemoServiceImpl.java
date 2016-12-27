package co.bugu.sourcecode.service.impl;

import co.bugu.sourcecode.model.Demo;
import co.bugu.sourcecode.service.IDemoService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daocers on 2016/7/26.
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Demo demo) {
        return baseDao.insert("mall.demo.insert", demo);
    }

    @Override
    public int updateById(Demo demo) {
        return baseDao.update("mall.demo.updateById", demo);
    }

    @Override
    public int delete(Demo demo) {
        return baseDao.delete("mall.demo.deleteById", demo);
    }

    @Override
    public Demo findByObject(Demo demo) {
        return baseDao.selectOne("mall.demo.selectByObject", demo);
    }

    @Override
    public List<Demo> findAllByObject(Demo demo) {
        return baseDao.selectList("mall.demo.listByObject", demo);
    }

    @Override
    public PageInfo listByObject(Demo demo, PageInfo<Demo> pageInfo) throws Exception {
        return baseDao.listByObject("mall.demo.listByObject", demo, pageInfo);
    }
}
