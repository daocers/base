package co.bugu.tes.service;

import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Authority;

import java.util.List;

/**
 * Created by user on 2017/1/7.
 */
public interface IAuthorityService extends IBaseService<Authority> {
    void batchUpdate(Authority auth);

    void rebuildInfo(List<Authority> authorityList);
}
