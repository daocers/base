package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Authority;
import co.bugu.tes.service.IAuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/1/7.
 */
@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements IAuthorityService {
    @Override
    public void batchUpdate(Authority auth) {

    }

    @Override
    public void rebuildInfo(List<Authority> authorityList) {

    }
}
