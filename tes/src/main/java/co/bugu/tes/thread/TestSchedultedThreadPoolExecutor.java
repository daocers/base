package co.bugu.tes.thread;

import co.bugu.tes.model.SystemParam;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2017/2/16.
 */
public class TestSchedultedThreadPoolExecutor {
    public static void main(String[] args){
        ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("+++++");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);

        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前时间：" + System.currentTimeMillis()/1000);
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
    }

}
