package co.bugu.springboot.mystarter;

import co.bugu.springboot.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

/**
 * Created by user on 2017/6/5.
 */
@RestController
@SpringBootApplication
public class Application {
    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String index(){
        return helloService.sayHello();
    }

    public static void main(String[] args){
        DecimalFormat format = new DecimalFormat("#,###.00");
        System.out.println(format.format(0));
//        SpringApplication.run(Application.class, args);
    }
}
