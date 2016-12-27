package co.bugu.tes.service;


import co.bugu.tes.model.Child;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IChildService {
    int save(Child child);

    int updateById(Child child);

    int saveOrUpdate(Child child);

    int delete(Child child);

    Child findById(Integer id);

    List<Child> findAllByObject(Child child);

    PageInfo listByObject(Child child, PageInfo<Child> pageInfo) throws Exception;

}
