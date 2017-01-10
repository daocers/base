package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Role;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;
import java.util.Map;

public interface IRoleService extends IBaseService<Role>{
//    int save(Role role);
//
//    int updateById(Role role);

    /**
     * 保存，
     * 根据role是否有id，判断是更新还是插入
     * @param role
     * @param xList
     */
    void save(Role role, List<Map<String, Integer>> xList);

//    int delete(Role role);
//
//    Role findById(Integer id);
//
//    List<Role> findAllByObject(Role role);
//
//    PageInfo listByObject(Role role, PageInfo<Role> pageInfo) throws Exception;

}
