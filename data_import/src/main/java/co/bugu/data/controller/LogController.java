package co.bugu.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/4/10.
 */
@Controller
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    private static Logger mqLogger = LoggerFactory.getLogger("MQ");

    @RequestMapping("/log")
    @ResponseBody
    public String log() {
        logger.debug("debug: {}", "this is a log of debug level");
        logger.info("info: {}", "this is a log of info level");
        logger.warn("warn: {}", "this is a log of warn level");
        logger.error("error: {}", "this is a log of error level");

        mqLogger.debug("mq debug log");
        mqLogger.error("mq异常", new Exception("空指针，哈哈哈"));
        Animal animal = null;
        try {
            String age = animal.age;
        } catch (Exception e) {
            logger.error("操作失败", e);
        }

        String name = animal.name;
        return "good";
    }
}

class Animal {
    String name;
    String age;
}