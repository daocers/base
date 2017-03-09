package co.bugu.thread;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Created by daocers on 2017/3/9.
 */
public class SemaphoreTest {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        MyRunnable10 runnable = new MyRunnable10(semaphore);
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}

class MyRunnable10 implements Runnable{
    private Semaphore semaphore;
    public MyRunnable10(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("获取到信号");
            System.out.println("当前时间：" + new Date());
            Thread.sleep(3000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
