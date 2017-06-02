package co.bugu.springboot.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/6/2.
 */
@ComponentScan
@Configuration
@RestController
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/name")
    public String getFileName(){
        return "filename....";
    }
}
