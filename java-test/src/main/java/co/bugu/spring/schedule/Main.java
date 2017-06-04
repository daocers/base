package co.bugu.spring.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by daocers on 2017/6/4.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }
}
