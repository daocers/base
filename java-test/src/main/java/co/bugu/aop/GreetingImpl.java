package co.bugu.aop;

/**
 * Created by daocers on 2016/10/26.
 */
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        before();
        System.out.println("hello! " + name);
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}
