package co.bugu.springboot.main;

import co.bugu.springboot.controller.RootController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2017/6/2.
 */
//@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args){
//        SpringApplication.run(Application.class, args);
        SpringApplication.run(UserController.class, args);
//        SpringApplication.run(RootController.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @Bean
    public EmbeddedServletContainerFactory servletFactory(){
        TomcatEmbeddedServletContainerFactory tomcatFactory =
                new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8011);
        tomcatFactory.setSessionTimeout(10, TimeUnit.SECONDS);
        return tomcatFactory;
    }
}
