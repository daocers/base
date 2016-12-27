package co.bugu.aop;

/**
 * Created by daocers on 2016/10/26.
 */
public class GreetingProxy implements Greeting {
    private GreetingImpl greeting;

    public GreetingProxy(GreetingImpl greeting){
        this.greeting = greeting;
    }

    @Override
    public void sayHello(String name) {
        before();
        greeting.sayHello(name);
        after();

    }

    private void before(){

    }

    private void after(){

    }
}
