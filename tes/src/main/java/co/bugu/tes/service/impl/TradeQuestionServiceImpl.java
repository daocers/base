package co.bugu.tes.service.impl;

import co.bugu.tes.model.TradeQuestion;
import co.bugu.tes.service.ITradeQuestionService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
/**
* .
*/
@Service
public class TradeQuestionServiceImpl extends BaseServiceImpl<TradeQuestion> implements ITradeQuestionService {
    @Override
    protected String getProjectName() {
        return "tes";
    }

}
