package co.bugu.threadTest;

import java.util.Date;

/**
 * Created by daocers on 2016/11/3.
 */
public class Task implements Runnable {
    private Date initDate;
    private String name;

    public Task(String name){
        initDate = new Date();
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread() + " task: " + this.name + " 创建时间：" + initDate);

        System.out.println("线程：" + Thread.currentThread() + " task: " + this.name + " 开始时间：" + new Date());
        try{
            Long duration = (long)(Math.random() * 10);
            System.out.println("线程： " + Thread.currentThread()  + duration + "seconds");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("线程： " + Thread.currentThread() + " finished on " + new Date());
    }
}
