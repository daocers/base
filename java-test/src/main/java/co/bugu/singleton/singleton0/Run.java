package co.bugu.singleton.singleton0;

/**
 * Created by user on 2017/4/11.
 */
public class Run {
    public static void main(String[] args){
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
