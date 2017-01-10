package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Question;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;
import java.util.Map;

public interface IQuestionService extends IBaseService<Question>{
    int save(Question question, List<Map<String, Integer>> xList);

//    int updateById(Question question);

    int saveOrUpdate(Question question, List<Map<String, Integer>> xList);

//    int delete(Question question);

//    Question findById(Integer id);

//    List<Question> findAllByObject(Question question);

//    PageInfo listByObject(Question question, PageInfo<Question> pageInfo) throws Exception;

    List<Map<String, Object>> selectCountOfPropInfo(Integer metaInfoId);

    int batchAdd(List<Question> questionList);
}
