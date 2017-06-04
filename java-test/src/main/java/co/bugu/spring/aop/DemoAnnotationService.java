package co.bugu.spring.aop;

import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2017/6/2.
 */
@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void add(){
        System.out.println("add in annoationService");
    }
}
