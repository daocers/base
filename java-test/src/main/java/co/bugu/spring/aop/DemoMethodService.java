package co.bugu.spring.aop;

import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2017/6/2.
 */
@Service
public class DemoMethodService {
    public void add(){
        System.out.println("add int method");
    }
}
