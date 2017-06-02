package co.bugu.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/6/2.
 */
@RestController
@SpringBootApplication
public class MainApplication implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setPort(8081);
    }

    public static void main(String[] args){
        SpringApplication.run(FileController.class, args);
    }

    @RequestMapping("/main")
    public String testPort(){
        return "hello 端口8081";
    }
}
