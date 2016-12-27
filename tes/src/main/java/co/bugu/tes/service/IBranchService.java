package co.bugu.tes.service;


import co.bugu.tes.model.Branch;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IBranchService {
    int save(Branch branch);

    int updateById(Branch branch);

    int saveOrUpdate(Branch branch);

    int delete(Branch branch);

    void deleteAll();

    Branch findById(Integer id);

    List<Branch> findAllByObject(Branch branch);

    PageInfo listByObject(Branch branch, PageInfo<Branch> pageInfo) throws Exception;

}
