package co.bugu.spring.annotation1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by user on 2017/6/2.
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.say("java config"));
        context.close();
    }
}
