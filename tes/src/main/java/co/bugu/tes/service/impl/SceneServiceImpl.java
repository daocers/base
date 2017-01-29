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
    @Override
    public Scene findById(Integer id) {
        Scene scene = baseDao.selectOne("tes.scene.selectById", id);
        if(scene.getDepartmentId() != null){
            scene.setDepartment(baseDao.selectOne("tes.department.selectById", scene.getDepartmentId()));
        }
        if(scene.getBranchId() != null){
            scene.setBranch(baseDao.selectOne("tes.branch.selectById", scene.getBranchId()));
        }
        if(scene.getStationId() != null){
            scene.setStation(baseDao.selectOne("tes.station.selectById", scene.getStationId()));
        }
        if(scene.getCreateUserId() != null){
            scene.setCreateUser(baseDao.selectOne("tes.user.selectById", scene.getCreateUser()));
        }
        if(scene.getUpdateUserId() != null){
            if(scene.getUpdateUserId() == scene.getCreateUserId()){
                scene.setUpdateUser(scene.getCreateUser());
            }else{
                scene.setUpdateUser(baseDao.selectOne("tes.user.selectById", scene.getUpdateUserId()));
            }

        }
        return scene;
    }
//
//    @Override
//    public List<Scene> findAllByObject(Scene scene) {
//        return baseDao.selectList("tes.scene.listByObject", scene);
//    }
//
    @Override
    public PageInfo findByObject(Scene scene, PageInfo<Scene> pageInfo) throws Exception {
        pageInfo =  baseDao.listByObject("tes.scene.findByObject", scene, pageInfo);
        for(Scene item: pageInfo.getData()){
            if(item.getDepartmentId() != null){
                item.setDepartment(baseDao.selectOne("tes.department.selectById", item.getDepartmentId()));
            }
            if(item.getBranchId() != null){
                item.setBranch(baseDao.selectOne("tes.branch.selectById", item.getBranchId()));
            }
            if(item.getStationId() != null){
                item.setStation(baseDao.selectOne("tes.station.selectById", item.getStationId()));
            }
        }
        return  pageInfo;

    }
}
