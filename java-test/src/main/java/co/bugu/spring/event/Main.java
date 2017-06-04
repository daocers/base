package co.bugu.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by daocers on 2017/6/3.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("hello application event");
        context.close();
    }
}
