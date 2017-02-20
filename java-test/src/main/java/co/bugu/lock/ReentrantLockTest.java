package co.bugu.lock;

import co.bugu.framework.core.constant.SysProperty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 2017/2/20.
 */
public class ReentrantLockTest {
    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args){
        ReentrantLockTest test = new ReentrantLockTest();
        test.testLock();

    }

    public void testLock(){
        for(int i = 0;i < 5; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sayHello();
                }
            }, "thread " + i);
            thread.start();
        }
    }

    public void sayHello(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locking...");
            System.out.println("hello");
            System.out.println(Thread.currentThread().getName() + " unlocking");
            System.out.println("--------------");
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
