package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Question;

import java.util.List;
import java.util.Map;

public interface IQuestionService extends IBaseService<Question> {

    void saveOrUpdate(Question question, List<Map<String, Integer>> xList);

    int batchAdd(List<Question> questionList);

    List<Map<String,Object>> selectCountOfPropInfo(Integer metaInfoId);
}
