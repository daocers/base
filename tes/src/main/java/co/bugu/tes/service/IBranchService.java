package co.bugu.tes.service;

import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Branch;

public interface IBranchService extends IBaseService<Branch> {

    void deleteAll();
}
