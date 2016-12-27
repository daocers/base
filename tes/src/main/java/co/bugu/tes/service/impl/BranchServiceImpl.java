package co.bugu.tes.service.impl;


import co.bugu.tes.model.Branch;
import co.bugu.tes.service.IBranchService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements IBranchService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Branch branch) {
        return baseDao.insert("tes.branch.insert", branch);
    }

    @Override
    public int updateById(Branch branch) {
        return baseDao.update("tes.branch.updateById", branch);
    }

    @Override
    public int saveOrUpdate(Branch branch) {
        if(branch.getId() == null){
            return baseDao.insert("tes.branch.insert", branch);
        }else{
            return baseDao.update("tes.branch.updateById", branch);
        }
    }

    @Override
    public int delete(Branch branch) {
        return baseDao.delete("tes.branch.deleteById", branch);
    }

    @Override
    public void deleteAll() {
        baseDao.delete("tes.branch.deleteAll");
    }

    @Override
    public Branch findById(Integer id) {
        return baseDao.selectOne("tes.branch.selectById", id);
    }

    @Override
    public List<Branch> findAllByObject(Branch branch) {
        return baseDao.selectList("tes.branch.listByObject", branch);
    }

    @Override
    public PageInfo listByObject(Branch branch, PageInfo<Branch> pageInfo) throws Exception {
        return baseDao.listByObject("tes.branch.listByObject", branch, pageInfo);
    }
}
