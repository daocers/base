package co.bugu.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by user on 2017/6/2.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService service = context.getBean(UseFunctionService.class);
        System.out.println(service.sayHello("di"));
        context.close();
    }
}
