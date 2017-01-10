package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Branch;
import co.bugu.tes.service.IBranchService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl extends BaseServiceImpl<Branch> implements IBranchService {


    @Override
    public void deleteAll() {
        baseDao.delete("tes.branch.deleteAll");
    }

}
