package co.bugu.tes.service.impl;


import co.bugu.tes.model.Paper;
import co.bugu.tes.model.Scene;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IPaperService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements IPaperService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Paper paper) {
        return baseDao.insert("tes.paper.insert", paper);
    }

    @Override
    public int updateById(Paper paper) {
        return baseDao.update("tes.paper.updateById", paper);
    }

    @Override
    public int saveOrUpdate(Paper paper) {
        if(paper.getId() == null){
            return baseDao.insert("tes.paper.insert", paper);
        }else{
            return baseDao.update("tes.paper.updateById", paper);
        }
    }

    @Override
    public int delete(Paper paper) {
        return baseDao.delete("tes.paper.deleteById", paper);
    }

    @Override
    public Paper findById(Integer id) {
        return baseDao.selectOne("tes.paper.selectById", id);
    }

    @Override
    public List<Paper> findAllByObject(Paper paper) {
        return baseDao.selectList("tes.paper.listByObject", paper);
    }

    @Override
    public PageInfo listByObject(Paper paper, PageInfo<Paper> pageInfo) throws Exception {
        return baseDao.listByObject("tes.paper.listByObject", paper, pageInfo);
    }

    @Override
    public boolean generateAllPaper(Scene scene) {
        return false;
    }

    @Override
    public boolean generatePaperForUser(Scene scene, User user) {
        return false;
    }

    @Override
    public boolean disabledUserOfScene(Scene scene, User user) {
        return false;
    }
}
