package co.bugu.jdk8.lockSupport;

import clojure.lang.Obj;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by user on 2017/3/3.
 */
public class Test {
    static Object u = new Object();
    static TestSuspendThread t1 = new TestSuspendThread("t1");
    static TestSuspendThread t2 = new TestSuspendThread("t2");
    public static class TestSuspendThread extends Thread{
        public TestSuspendThread(String name){
            setName(name);
        }
        public void run(){
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();

        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}
