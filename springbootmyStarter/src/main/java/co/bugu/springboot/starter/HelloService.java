package co.bugu.springboot.starter;

/**
 * Created by user on 2017/6/5.
 */
public class HelloService {
    private String msg;

    public String sayHello(){
        return "Hello " + msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
