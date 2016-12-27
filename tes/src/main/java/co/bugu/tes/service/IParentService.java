package co.bugu.tes.service;


import co.bugu.tes.model.Parent;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IParentService {
    int save(Parent parent);

    int updateById(Parent parent);

    int saveOrUpdate(Parent parent);

    int delete(Parent parent);

    Parent findById(Integer id);

    List<Parent> findAllByObject(Parent parent);

    PageInfo listByObject(Parent parent, PageInfo<Parent> pageInfo) throws Exception;

}
