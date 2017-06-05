package co.bugu.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/6/2.
 */
@ComponentScan
@RestController
@Configuration
public class RedisController {
    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
//    StringRedisTemplate stringRedisTemplate;

//    @Resource(name = "stringRedisTemplate")
//    ValueOperations<String, String> valOprStr;

    @RequestMapping("/redis")
    public String redis(){
        return "null";
    }
}
