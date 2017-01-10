package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Scene;
import co.bugu.tes.service.ISceneService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneServiceImpl extends BaseServiceImpl<Scene> implements ISceneService {
//    @Autowired
//    BaseDao baseDao;
//
//    @Override
//    public int save(Scene scene) {
//        return baseDao.insert("tes.scene.insert", scene);
//    }
//
//    @Override
//    public int updateById(Scene scene) {
//        return baseDao.update("tes.scene.updateById", scene);
//    }
//
//    @Override
//    public int saveOrUpdate(Scene scene) {
//        if(scene.getId() == null){
//            return baseDao.insert("tes.scene.insert", scene);
//        }else{
//            return baseDao.update("tes.scene.updateById", scene);
//        }
//    }
//
//    @Override
//    public int delete(Scene scene) {
//        return baseDao.delete("tes.scene.deleteById", scene);
//    }
//
//    @Override
//    public Scene findById(Integer id) {
//        return baseDao.selectOne("tes.scene.selectById", id);
//    }
//
//    @Override
//    public List<Scene> findAllByObject(Scene scene) {
//        return baseDao.selectList("tes.scene.listByObject", scene);
//    }
//
//    @Override
//    public PageInfo listByObject(Scene scene, PageInfo<Scene> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.scene.listByObject", scene, pageInfo);
//    }
}
