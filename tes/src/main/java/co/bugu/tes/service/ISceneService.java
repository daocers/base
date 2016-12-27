package co.bugu.tes.service;


import co.bugu.tes.model.Scene;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface ISceneService {
    int save(Scene scene);

    int updateById(Scene scene);

    int saveOrUpdate(Scene scene);

    int delete(Scene scene);

    Scene findById(Integer id);

    List<Scene> findAllByObject(Scene scene);

    PageInfo listByObject(Scene scene, PageInfo<Scene> pageInfo) throws Exception;

}
