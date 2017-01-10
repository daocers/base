package co.bugu.tes.service;

import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/1/9.
 */
public interface IRoleService extends IBaseService<Role> {
    void save(Role role, List<Map<String, Integer>> xList);
}
