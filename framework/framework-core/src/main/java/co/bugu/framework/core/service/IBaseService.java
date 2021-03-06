package co.bugu.framework.core.service;


import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

/**
 * Created by record on 2017/1/5.
 */
public interface IBaseService<T> {
    int save(T record);

    int updateById(T record);

    int delete(T record);

    T findById(Integer id);

    List<T> findByObject(T record);

    PageInfo findByObject(T record, PageInfo<T> pageInfo) throws Exception;

    PageInfo findByObject(T record, Integer showCount, Integer curPage) throws Exception;
}
