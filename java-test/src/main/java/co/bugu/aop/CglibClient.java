package co.bugu.aop;

/**
 * Created by daocers on 2016/10/27.
 */
public class CglibClient {
    public static void main(String[] args){
        Greeting greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("allen");

    }
}
