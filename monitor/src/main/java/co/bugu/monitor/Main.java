package co.bugu.monitor;

import co.bugu.monitor.controller.TestController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by QDHL on 2017/7/18.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                 new ClassPathXmlApplicationContext("classpath:/spring/spring-beans.xml");
        context.start();
        TestController controller = context.getBean(TestController.class);
        controller.test();
    }
}
