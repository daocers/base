package co.bugu.jdk8.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by user on 2017/3/3.
 * <p>
 * <p>
 * CyclicBarrier 和countdownlatch相似，也是等地啊某些线程都做完之后再执行。
 * 与countDownlatch区别在于这个计数器可以反复使用。比如，为我们将计数器设置为10，
 * 那么凑齐第一批10个线程之后，计数器就会归零，然后接着凑齐下一批10个线程。
 */
public class Test implements Runnable {
    private String soldier;
    private final CyclicBarrier cyclic;

    public Test(String soldier, CyclicBarrier cyclic) {
        this.soldier = soldier;
        this.cyclic = cyclic;
    }

    @Override
    public void run() {
        try {
            //等待所有士兵到齐
            cyclic.await();
            doWork();
            cyclic.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(soldier + " : done");
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int n;

        public BarrierRun(boolean flag, int n) {
            super();
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println(n + "个任务完成");
            } else {
                System.out.println(n + "个集合完成");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        Thread[] threads = new Thread[n];
        boolean flag = false;
        CyclicBarrier barrier = new CyclicBarrier(n, new BarrierRun(flag, n));
        System.out.println("集合");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " 报道");
            threads[i] = new Thread(new Test("士兵" + i, barrier));
            threads[i].start();
        }
    }
}
