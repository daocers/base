package co.bugu.springboot.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by user on 2017/6/2.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(LogController.class, args);
    }
}
