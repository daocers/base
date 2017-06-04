package co.bugu.spring.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by daocers on 2017/6/4.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionalConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name"));
        System.out.println(listService.showListCmd());
    }
}
