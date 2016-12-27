package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/4.
 */
public class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("运行中。。。");
    }

    public static void main(String[] args){
        Runnable runnable = new MyThread2();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("运行结束");
    }
}
