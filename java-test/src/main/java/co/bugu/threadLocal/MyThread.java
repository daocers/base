package co.bugu.threadLocal;

/**
 * Created by daocers on 2016/11/3.
 */
public class MyThread extends Thread {
    private static int num = 0;
    public MyThread(){
        num++;
    }
    @Override
    public void run(){
        System.out.println("第"  + num + "个线程");
    }
}
