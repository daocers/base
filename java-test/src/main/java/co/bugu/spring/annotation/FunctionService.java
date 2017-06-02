package co.bugu.spring.annotation;

import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/6/2.
 */
@Service
public class FunctionService {
    public String sayHello(String word){
        return "hello " + word;
    }
}
