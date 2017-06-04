package co.bugu.spring.groupAnnotation;

import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2017/6/4.
 */
@Service
public class DemoService {
    public void outputResult(){
        System.out.println("从组合注解中获取到的bean");
    }
}
