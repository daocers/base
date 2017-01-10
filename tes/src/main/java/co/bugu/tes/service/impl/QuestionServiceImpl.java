package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Question;
import co.bugu.tes.service.IQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService {
//    @Autowired
//    BaseDao baseDao;

    @Override
    public int save(Question question, List<Map<String, Integer>> xList) {
        int num = baseDao.insert("tes.question.insert", question);
        for (Map<String, Integer> map : xList) {
            map.put("questionId", question.getId());
            baseDao.insert("tes.question.addToPropItem", map);
        }
        return num;
    }

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
        }
        return num;
    }

//    @Override
//    public int delete(Question question) {
//        return baseDao.delete("tes.question.deleteById", question);
//    }
//
//    @Override
//    public Question findById(Integer id) {
//        return baseDao.selectOne("tes.question.selectById", id);
//    }
//
//    @Override
//    public List<Question> findAllByObject(Question question) {
//        return baseDao.selectList("tes.question.listByObject", question);
//    }
//
//    @Override
//    public PageInfo listByObject(Question question, PageInfo<Question> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.question.listByObject", question, pageInfo);
//    }
//
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
}
