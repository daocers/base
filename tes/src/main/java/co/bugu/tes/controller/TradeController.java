package co.bugu.tes.controller;

import co.bugu.tes.service.ITradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    ITradeService tradeService;

    private static Logger logger = LoggerFactory.getLogger(TradeController.class);

    public String record(){
        return "trade/record";
    }
}
