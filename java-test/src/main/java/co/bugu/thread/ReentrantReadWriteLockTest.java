package co.bugu.thread;

import sun.rmi.runtime.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by user on 2017/3/9.
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] arg) throws InterruptedException {
        MyRunnable1 runnable = new MyRunnable1();
        MyRunnable2 runnable2 = new MyRunnable2();
        ExecutorService executors = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 100; i++){
//            executors.submit(runnable);
            executors.submit(runnable2);
        }
        Thread.sleep(3000);
        System.out.println("the finally age is :" + runnable2.age);
        executors.shutdown();
    }
}


class MyRunnable1 implements Runnable{
    private Integer age = 1;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    @Override
    public void run() {
        try{
            boolean hasLock = readLock.tryLock();
            if(hasLock){
                System.out.println("current age is :" + age);
            }else{
                System.out.println("获取读锁失败");
            }
        }finally {
            readLock.unlock();
        }

        try{
            boolean hasLock = writeLock.tryLock();
            if(hasLock){
                age++;
                System.out.println("age add 1.");
            }else{
                System.out.println("获取写锁失败");
            }
        }finally {
            writeLock.unlock();
        }
    }
}

class MyRunnable2 implements Runnable{
    int age = 0;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    @Override
    public void run() {
        try{
            readLock.lock();
            System.out.println("当前age: " + age);
        }finally {
            readLock.unlock();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
          writeLock.lock();
          age++;
          System.out.println("age 增加 1");
        }finally {
            writeLock.unlock();
        }
    }
}