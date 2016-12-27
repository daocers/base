package co.bugu.aop;

/**
 * Created by daocers on 2016/10/26.
 */
public class Client {

    public static void main(String[] args){
        Greeting greeting = new GreetingProxy(new GreetingImpl());
        greeting.sayHello("jack");
    }
}
