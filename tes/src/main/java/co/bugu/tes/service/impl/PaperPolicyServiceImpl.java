package co.bugu.tes.service.impl;


import co.bugu.tes.model.PaperPolicy;
import co.bugu.tes.service.IPaperPolicyService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperPolicyServiceImpl implements IPaperPolicyService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(PaperPolicy paperpolicy) {
        return baseDao.insert("tes.paperpolicy.insert", paperpolicy);
    }

    @Override
    public int updateById(PaperPolicy paperpolicy) {
        return baseDao.update("tes.paperpolicy.updateById", paperpolicy);
    }

    @Override
    public int saveOrUpdate(PaperPolicy paperpolicy) {
        if(paperpolicy.getId() == null){
            return baseDao.insert("tes.paperpolicy.insert", paperpolicy);
        }else{
            return baseDao.update("tes.paperpolicy.updateById", paperpolicy);
        }
    }

    @Override
    public int delete(PaperPolicy paperpolicy) {
        return baseDao.delete("tes.paperpolicy.deleteById", paperpolicy);
    }

    @Override
    public PaperPolicy findById(Integer id) {
        return baseDao.selectOne("tes.paperpolicy.selectById", id);
    }

    @Override
    public List<PaperPolicy> findAllByObject(PaperPolicy paperpolicy) {
        return baseDao.selectList("tes.paperpolicy.listByObject", paperpolicy);
    }

    @Override
    public PageInfo listByObject(PaperPolicy paperpolicy, PageInfo<PaperPolicy> pageInfo) throws Exception {
        return baseDao.listByObject("tes.paperpolicy.listByObject", paperpolicy, pageInfo);
    }
}
