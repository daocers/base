package co.bugu.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by daocers on 2017/6/3.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PrePostConfig.class);

        BeanWayService beanWayService = context.getBean(BeanWayService.class);

        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

        context.close();
    }
}
