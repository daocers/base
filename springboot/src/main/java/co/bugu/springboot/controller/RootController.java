package co.bugu.springboot.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/6/2.
 */
@RestController
@ComponentScan
@Configuration
@RequestMapping("/root")
public class RootController {
    public static final String PATH_ROOT = "/";

    @RequestMapping(PATH_ROOT)
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
