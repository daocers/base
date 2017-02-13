package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Paper;
import co.bugu.tes.model.PaperPolicy;
import co.bugu.tes.model.Scene;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IPaperService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl extends BaseServiceImpl<Paper> implements IPaperService {
//    @Autowired
//    BaseDao baseDao;
//
//    @Override
//    public int save(Paper paper) {
//        return baseDao.insert("tes.paper.insert", paper);
//    }
//
//    @Override
//    public int updateById(Paper paper) {
//        return baseDao.update("tes.paper.updateById", paper);
//    }
//
//    @Override
//    public int saveOrUpdate(Paper paper) {
//        if(paper.getId() == null){
//            return baseDao.insert("tes.paper.insert", paper);
//        }else{
//            return baseDao.update("tes.paper.updateById", paper);
//        }
//    }
//
//    @Override
//    public int delete(Paper paper) {
//        return baseDao.delete("tes.paper.deleteById", paper);
//    }
//
//    @Override
//    public Paper findById(Integer id) {
//        return baseDao.selectOne("tes.paper.selectById", id);
//    }
//
//    @Override
//    public List<Paper> findAllByObject(Paper paper) {
//        return baseDao.selectList("tes.paper.listByObject", paper);
//    }
//
//    @Override
//    public PageInfo listByObject(Paper paper, PageInfo<Paper> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.paper.listByObject", paper, pageInfo);
//    }

    @Override
    public boolean generateAllPaper(Scene scene) throws Exception {
        if (scene.getId() == null) {
            throw new Exception("场次编号不能为空");
        }
        scene = baseDao.selectOne("tes.scene.selectById", scene.getId());
        if (scene == null) {
            throw new Exception("未找到对应的场次信息");
        }
        String joinUser = scene.getJoinUser();
        if (StringUtils.isEmpty(joinUser)) {
            throw new Exception("该场次没有选择参考人员");
        }
        List<Integer> userIds = JSON.parseArray(joinUser, Integer.class);
        Integer paperPolicyId = scene.getPaperPolicyId();
        if(paperPolicyId == null){
            throw new Exception("该场次没有选择试卷生成策略");
        }
        PaperPolicy paperPolicy = baseDao.selectOne("tes.paperPolicy.selectById", paperPolicyId);
        for (Integer id : userIds) {

        }
        return true;
    }

    @Override
    public boolean generatePaperForUser(Scene scene, User user) {
        return false;
    }

}
