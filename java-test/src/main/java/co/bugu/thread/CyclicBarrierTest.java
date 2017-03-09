package co.bugu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by daocers on 2017/3/9.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(11);
        MyRunnable8 runnable = new MyRunnable8(barrier);
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        barrier.await();

        System.out.println("到齐了，开始。");

//        MyRunnable9 runnable9 = new MyRunnable9(barrier);
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        new Thread(runnable9).start();
//        barrier.await();
//        System.out.println("干完活，结束。");

    }
}

class MyRunnable8 implements Runnable{
    CyclicBarrier barrier;
    public MyRunnable8(){

    }
    public MyRunnable8(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            Thread.sleep(300);
            System.out.println("报道...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable9 implements Runnable{
    private CyclicBarrier barrier;
    public MyRunnable9(CyclicBarrier barrier){
        this.barrier = barrier;
    }
    @Override
    public void run() {
        try{
            barrier.await();
            System.out.println("干活...");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
