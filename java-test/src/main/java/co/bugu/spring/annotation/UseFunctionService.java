package co.bugu.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/6/2.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService service;

    public String sayHello(String word){
        return service.sayHello(word);
    }
}
