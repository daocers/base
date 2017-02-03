package co.bugu.tes.service.impl;


import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.framework.util.JedisUtil;
import co.bugu.framework.util.exception.TesException;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Question;
import co.bugu.tes.service.IQuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService {
//    @Autowired
//    BaseDao baseDao;

//    @Override
//    public int save(Question question, List<Map<String, Integer>> xList) {
//        int num = baseDao.insert("tes.question.insert", question);
//        for (Map<String, Integer> map : xList) {
//            map.put("questionId", question.getId());
//            baseDao.insert("tes.question.addToPropItem", map);
//
//            //            将属性信息添加到redis中的set上
//            Integer propItemId = map.get("propItemId");
//            JedisUtil.sadd(Constant.QUESTION_PROPITEM_ID + propItemId, question.getId() + "");
//        }
//        return num;
//    }

//    @Override
//    public int updateById(Question question) {
//        return baseDao.update("tes.question.updateById", question);
//    }


    @Override
    public int saveOrUpdate(Question question, List<Map<String, Integer>> xList) {
        int num;
        if (question.getId() == null) {
            num = baseDao.insert("tes.question.insert", question);
        } else {
            num = baseDao.update("tes.question.updateById", question);

            baseDao.delete("tes.question.removeFromPropItem", question.getId());
        }
        for (Map<String, Integer> map : xList) {
            map.put("questionId", question.getId());
            baseDao.insert("tes.question.addToPropItem", map);

//            将属性信息添加到redis中的set上
//            QUESTION_PROPITEM_ID_ + 题型id + 属性id
            Integer propItemId = map.get("propItemId");
            JedisUtil.sadd(Constant.QUESTION_PROPITEM_ID + question.getMetaInfoId() + "_" + propItemId, question.getId() + "");
        }
        return num;
    }

    @Override
    public List<Map<String, Object>> selectCountOfPropInfo(Integer metaInfoId) {
        return baseDao.selectList("tes.question.selectCountOfPropInfo", metaInfoId);
    }

    @Override
    public int batchAdd(List<Question> questionList) {
        int num = 0;
        for(Question question: questionList){
            baseDao.insert("tes.question.insert", question);
            num++;
        }
        return num;
    }

    @Override
    public Set<String> findQuestionByPropItemId(Integer questionMetaInfoId, Integer... ids) throws TesException{
        String[] keys = new String[ids.length];
        for(int i = 0; i < ids.length; i++){
            keys[i] = Constant.QUESTION_PROPITEM_ID + questionMetaInfoId + "_"+ ids[i];
        }
        return JedisUtil.sinterForObj(keys);
    }

    @Override
    public int getCountByPropItemId(Integer questionMetaInfoId, Integer... ids) throws TesException {
        String[] keys = new String[ids.length];
        for(int i = 0; i < ids.length; i++){
            keys[i] = Constant.QUESTION_PROPITEM_ID +questionMetaInfoId + "_" + ids[i];
        }
        return JedisUtil.sinterForSize(keys);
    }

    @Override
    public PageInfo findByObject(Question record, Integer showCount, Integer curPage) throws Exception {
        PageInfo<Question> pageInfo = new PageInfo<Question>(showCount, curPage);
        baseDao.listByObject("tes.question.findByObject", record, pageInfo);
        if(pageInfo.getData().size() > 0){
            for(Question question: pageInfo.getData()){
                question.setPropertyItemList(baseDao.selectList("tes.question.findPropItemByQuestionId", question));
                question.setQuestionMetaInfo(baseDao.selectOne("tes.questionMetaInfo.selectById", question.getMetaInfoId()));
            }
        }
        return pageInfo;
    }


}
