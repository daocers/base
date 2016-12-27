package co.bugu.threadLearn;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by daocers on 2016/11/4.
 */
public class MyThread5 extends Thread {
    private int i = 5;
    @Override
    synchronized public void run(){
        System.out.println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());

    }

    public  static void main(String[] args){
        MyThread5 thread = new MyThread5();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
