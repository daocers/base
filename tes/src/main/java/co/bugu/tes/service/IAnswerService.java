package co.bugu.tes.service;

import co.bugu.tes.model.Answer;
import co.bugu.framework.core.service.IBaseService;

import java.util.Map;

/**
*
*/
public interface IAnswerService extends IBaseService<Answer> {
    boolean savePaperAnswer(Map<Integer, String> answerMap, Integer paperId);

}
