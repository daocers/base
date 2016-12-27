package co.bugu.dubbo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by daocers on 2016/10/12.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring/application.xml"});
        logger.info("启动provider....");
        context.start();
        logger.info("provider启动完毕");

        System.out.println("启动完毕...");
        System.in.read();
    }
}
