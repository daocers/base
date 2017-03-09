package co.bugu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/3/9.
 */
public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        MyRunnable3 runnable = new MyRunnable3();

        for(int i = 0; i < 100; i++){
            executors.submit(runnable);
        }
        Thread.sleep(3000);
        System.out.println(runnable.age);
        executors.shutdown();
    }
}

class MyRunnable3 implements Runnable{
    volatile int age = 0;
    @Override
    public void run() {
        System.out.println("age : " + ++age);
    }
}