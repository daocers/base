package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Question;
import co.bugu.tes.service.IQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService {

    @Override
    public void saveOrUpdate(Question question, List<Map<String, Integer>> xList) {

    }

    @Override
    public int batchAdd(List<Question> questionList) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectCountOfPropInfo(Integer metaInfoId) {
        return null;
    }
}

