package co.bugu.spring.annotation1;

/**
 * Created by user on 2017/6/2.
 */
public class UseFunctionService {
    FunctionService service;

    public FunctionService getService() {
        return service;
    }

    public void setService(FunctionService service) {
        this.service = service;
    }

    public String say(String word){
        return service.sayHello(word);
    }
}
