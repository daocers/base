package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.enums.PaperPolicyType;
import co.bugu.tes.enums.PaperType;
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
        if (paperPolicyId == null) {
            throw new Exception("该场次没有选择试卷生成策略");
        }
        PaperPolicy paperPolicy = baseDao.selectOne("tes.paperPolicy.selectById", paperPolicyId);
        String content = paperPolicy.getContent();
        if (StringUtils.isEmpty(content)) {
            throw new Exception("该试卷策略内容为空，请联系管理员确认!");
        }
//        获取paperpolicy，并连带获取关联的questionpolicy
        List<Integer> paperQeustionIdList = new ArrayList<>();
        List<HashMap> infos = JSON.parseArray(content, HashMap.class);

//        策略模式，需要将关联的试题策略查询出来
        if(paperPolicy.getSelectType() == PaperPolicyType.POLICY.getType()){
            List<QuestionPolicy> questionPolicyList = new ArrayList<>();
            for (Map map : infos) {
                Integer questionPolicyId = Integer.parseInt(map.get("questionPolicyId").toString());
                QuestionPolicy questionPolicy = baseDao.selectOne("tes.questionPolicy.selectById", questionPolicyId);
                questionPolicyList.add(questionPolicy);
            }
            paperPolicy.setQuestionPolicyList(questionPolicyList);
        }


        if (scene.getPaperType() == PaperType.UNIFY.getType()) {
            //统一试卷，每个人试题一致
            List<Integer> resQuesIds = getPaperQuestionIds(scene.getBankId(), paperPolicy);
            for (Integer userId : userIds) {
                savePaper(userId, scene.getId(), resQuesIds);
            }
        } else if (scene.getPaperType() == PaperType.RANDOM.getType()) {
            //每个人都随机，都不一样
            for (Integer userId : userIds) {
                List<Integer> resQuesIds = getPaperQuestionIds(scene.getBankId(), paperPolicy);
                savePaper(userId, scene.getId(), resQuesIds);
            }
        } else if (scene.getPaperType() == PaperType.IMPORT.getType()) {
            //教师导入试题
//            暂未开通，后续添加
        } else if (scene.getPaperType() == PaperType.DEEPRANDOM.getType()) {
            //深度随机，只设置需要的试题数量，随机生成试卷
        } else if (scene.getPaperType() == PaperType.DEEPUNIFY.getType()) {
//            深度统一，只设置需要的试题数量，随机生成试卷，每个用户统一
        }
        return true;
    }

    /**
     * 保存单个用户的试卷
     *
     * @param userId
     * @param sceneId
     * @param paperQuestionIdList
     */
    private void savePaper(Integer userId, Integer sceneId, List<Integer> paperQuestionIdList) {
        Paper paper = new Paper();
        paper.setUserId(userId);
        paper.setAnswerFlag(1);
        paper.setSceneId(sceneId);
        paper.setStatus(0);
        paper.setQuestionIds(JSON.toJSONString(paperQuestionIdList));
        baseDao.insert("tes.paper.insert", paper);
        int index = 0;
        for (Integer questionId : paperQuestionIdList) {
            Map<String, Integer> param = new HashMap<>();
            param.put("paperId", paper.getId());
            param.put("questionId", questionId);
            param.put("idx", index++);
            baseDao.insert("tes.paper.addQues", param);
        }
    }

    /**
     * 获取最终的试卷试题
     *
     * @param  bankId  题库，如果为空，表示从所有题库中随机选择
     * @param paperPolicy
     * @return
     */
    private List<Integer> getPaperQuestionIds(Integer bankId, PaperPolicy paperPolicy) throws Exception {
        List<Integer> res = new ArrayList<>();
        //策略模式
        if(paperPolicy.getSelectType() == PaperPolicyType.POLICY.getType()){
            List<QuestionPolicy> questionPolicyList = paperPolicy.getQuestionPolicyList();
            if (questionPolicyList == null || questionPolicyList.size() == 0) {
                throw new Exception("试题策略不能为空");
            }
            for (QuestionPolicy questionPolicy : questionPolicyList) {
                String quesContent = questionPolicy.getContent();
                if (StringUtils.isEmpty(quesContent)) {
                    throw new Exception("试题策略内容不能为空");
                }

//            int数组，最后一个元素是该属性组合的所选试题数量，前面的为试题属性组合
                List<Integer> propertyIds = JSON.parseArray(quesContent, Integer.class);
                Integer count = propertyIds.remove(propertyIds.size() - 1);
                Integer existCount = QuestionUtil.getCountByPropItemId(questionPolicy.getQuestionMetaInfoId(), bankId, propertyIds);
                if (existCount < count) {
                    throw new Exception("试题数量不足");
                }
                Set<String> questionIds = QuestionUtil.findQuestionByPropItemId(questionPolicy.getQuestionMetaInfoId(), bankId, propertyIds);
                List<Integer> quesIdList = new ArrayList<>();
                for (String id : questionIds) {
                    quesIdList.add(Integer.parseInt(id));
                }

                res.addAll(QuestionUtil.getResult(quesIdList, count));
            }
        }else{//普通模式
            String content = paperPolicy.getContent();
            List<Map> info = JSON.parseArray(content, Map.class);
            for(Map<String, String> map: info){
                Integer questionMetaInfoId = Integer.parseInt(map.get("questionMetaInfoId"));
                Integer count = Integer.parseInt(map.get("count"));
                Double score = Double.valueOf(map.get("score"));
                res.addAll(QuestionUtil.getResultByQuesMetaId(questionMetaInfoId, count));
            }
        }

        return res;
    }


    @Override
    public boolean generatePaperForUser(Scene scene, User user) {
        return false;
    }

}
