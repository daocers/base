package co.bugu.sourcecode.service;

import co.bugu.sourcecode.model.Demo;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

/**
 * Created by daocers on 2016/7/26.
 */
public interface IDemoService {
    int save(Demo demo);

    int updateById(Demo demo);

    int delete(Demo demo);

    Demo findByObject(Demo demo);

    List<Demo> findAllByObject(Demo demo);

    PageInfo listByObject(Demo demo, PageInfo<Demo> pageInfo) throws Exception;


}
