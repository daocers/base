package co.bugu.singleton.singleton1;

/**
 * Created by user on 2017/4/11.
 */
public class Run {
    public static void main(String[] args){
        MyThread t1 = new MyThread();
        t1.start();
        MyThread t2 = new MyThread();
        t2.start();
        new MyThread().start();
    }
}
