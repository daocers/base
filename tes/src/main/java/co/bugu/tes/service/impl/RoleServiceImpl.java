package co.bugu.tes.service.impl;

import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Role;
import co.bugu.tes.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/1/9.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Override
    public void save(Role role, List<Map<String, Integer>> xList) {

    }
}
