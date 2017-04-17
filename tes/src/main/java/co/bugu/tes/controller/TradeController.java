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

    /**
     * 录制交易
     * 打开交易界面， 同时录入信息，记录当前交易界面的栏位信息，顺便出题一道
     * @return
     */
    @RequestMapping("/toRecord")
    public String record(){
        return "trade/record";
    }

    @RequestMapping("/save")
    public String saveModelAndQuestion(){
        return null;
    }

    @RequestMapping("/saveQuestion")
    public String saveQuestion(){
        return null;
    }
}
