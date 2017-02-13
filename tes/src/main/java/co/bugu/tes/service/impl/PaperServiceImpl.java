package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.*;
import co.bugu.tes.service.IPaperService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.tes.util.QuestionUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        String content = paperPolicy.getContent();
        if(StringUtils.isEmpty(content)){
            throw new Exception("该试卷策略内容为空，请联系管理员确认!");
        }
//抽取出题逻辑
//        List<Integer> paperQuesIdList = getPaperQuestionIds(scene);
        List<Integer> paperQeustionIdList = new ArrayList<>();
        List<HashMap> infos = JSON.parseArray(content, HashMap.class);


        for (Integer userId : userIds) {
            for(Map map: infos){
                Integer questionMetaInfoId = Integer.parseInt(map.get("questionMetaInfoId").toString());
                Integer questionPolicyId = Integer.parseInt(map.get("questionPolicyId").toString());
                Double score = Double.parseDouble(map.get("score").toString());
                QuestionPolicy questionPolicy = baseDao.selectOne("tes.questionPolicy.selectById", questionPolicyId);
                if(questionPolicy == null){
                    throw new Exception("试题策略不能为空。");
                }
                String quesContent = questionPolicy.getContent();
                if(StringUtils.isEmpty(quesContent)){
                    throw new Exception("试题策略内容不能为空");
                }
                List<Integer> propertyIds = JSON.parseArray(quesContent, Integer.class);
                Integer count = questionPolicy.getCount();
                Integer existCount = QuestionUtil.getCountByPropItemId(questionMetaInfoId, propertyIds);
                if(existCount < count){
                    throw new Exception("试题数量不足");
                }
                Set<String> questionIds = QuestionUtil.findQuestionByPropItemId(questionMetaInfoId, propertyIds);
                List<Integer> quesIdList = new ArrayList<>();
                for(String id: questionIds){
                    quesIdList.add(Integer.parseInt(id));
                }

                paperQeustionIdList.addAll(QuestionUtil.getResult(quesIdList, count));
                Paper paper = new Paper();
                paper.setUserId(userId);
                paper.setAnswerFlag(1);
                paper.setSceneId(scene.getId());
                paper.setStatus(0);
                baseDao.insert("tes.paper.insert", paper);
                int index = 0;
                for(Integer questionId: paperQeustionIdList){
                    Map<String, Integer> param = new HashMap<>();
                    param.put("paperId", paper.getId());
                    param.put("questionId", questionId);
                    param.put("idx", index++);
                    baseDao.insert("tes.paper.addQues", param);
                }
            }
        }
        return true;
    }

    /**
     * 获取最终的试卷试题
     * @param scene
     * @return
     */
    private List<Integer> getPaperQuestionIds(Scene scene) {
        return null;
    }


    @Override
    public boolean generatePaperForUser(Scene scene, User user) {
        return false;
    }

}
