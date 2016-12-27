package co.bugu.aop;

/**
 * Created by daocers on 2016/10/26.
 */
public class JDKDynamicClient {
    public static void main(String[] args){
        Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        greeting.sayHello("zhangsan");
    }
}
