package co.bugu.springboot.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/6/2.
 */
@ComponentScan
@Configuration
@RestController
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log")
    public String helloworld(){
        logger.debug("log hello world");
        return "hello world";
    }

    @RequestMapping("/log/{name}")
    public String helloName(@PathVariable String name){
        logger.debug("访问helloname, name = {}", name);
        return "hllo " + name;
    }

}
