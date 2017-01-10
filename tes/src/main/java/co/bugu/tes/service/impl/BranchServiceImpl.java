package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Branch;
import co.bugu.tes.service.IBranchService;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends BaseServiceImpl<Branch> implements IBranchService {

    @Override
    public void deleteAll() {
        baseDao.delete("tes.branch.deleteAll");
    }
}

