package co.bugu.springboot.base;

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
public class SimpleController {
    @RequestMapping("/simple")
    public String simple(){
        return "simple";
    }
}
