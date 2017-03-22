package co.bugu.tes.service.impl;

import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Answer;
import co.bugu.tes.service.IAnswerService;
import org.springframework.stereotype.Service;
/**
* .
*/
@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements IAnswerService {
    @Override
    protected String getProjectName() {
        return "data";
    }

}
