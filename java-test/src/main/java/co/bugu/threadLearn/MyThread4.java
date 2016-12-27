package co.bugu.threadLearn;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by daocers on 2016/11/4.
 */
public class MyThread4 implements Runnable  {
    private int count = 5;

    @Override
    public synchronized void run() {
        count--;
        System.out.println("由 " + Thread.currentThread().getName() + "计算， count = " + count);
    }

    public static void main(String[] args){
        MyThread4 thread = new MyThread4();
        Thread a = new Thread(thread, "a");
        Thread b = new Thread(thread, "b");
        Thread c = new Thread(thread, "c");
        Thread d = new Thread(thread, "d");
        Thread e = new Thread(thread, "e");


        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
