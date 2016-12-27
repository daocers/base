package co.bugu.tes.service;


import co.bugu.tes.model.Page;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IPageService {
    int save(Page page);

    int updateById(Page page);

    int saveOrUpdate(Page page);

    int delete(Page page);

    Page findById(Integer id);

    List<Page> findAllByObject(Page page);

    PageInfo listByObject(Page page, PageInfo<Page> pageInfo) throws Exception;

}
