package co.bugu.tes.service.impl;

import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Answer;
import co.bugu.tes.model.Paper;
import co.bugu.tes.service.IAnswerService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
* .
*/
@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements IAnswerService {

    @Override
    public boolean savePaperAnswer(Map<Integer, String> answerMap, Integer paperId) {
        Iterator<Map.Entry<Integer, String>> iterator = answerMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, String> entry = iterator.next();
            Integer questionId = entry.getKey();
            String answerInfo = entry.getValue();
            Answer answer = new Answer();
            answer.setQuestionId(questionId);
            answer.setPaperId(paperId);
            answer.setAnswer(answerInfo);
            baseDao.insert("tes.answer.insert", answer);


        }
        return true;
    }
}
