package co.bugu.tes.service;


import co.bugu.tes.model.SystemParam;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface ISystemParamService {
    int save(SystemParam systemparam);

    int updateById(SystemParam systemparam);

    int saveOrUpdate(SystemParam systemparam);

    int delete(SystemParam systemparam);

    SystemParam findById(Integer id);

    List<SystemParam> findAllByObject(SystemParam systemparam);

    PageInfo listByObject(SystemParam systemparam, PageInfo<SystemParam> pageInfo) throws Exception;

}
