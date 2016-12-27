package co.bugu.aop.myaop;

import co.bugu.aop.Greeting;

/**
 * Created by daocers on 2016/10/27.
 */
public class GreetingImpl implements Greeting{
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    public void goodMorning(String name){
        System.out.println("GoodMorning " + name);
    }

    public void goodNight(String name){
        System.out.println("GoodNight " + name);

    }
}
