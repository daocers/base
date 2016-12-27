package co.bugu.thread;

import ch.qos.logback.classic.net.SyslogAppender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daocers on 2016/10/27.
 */
public class WaitTest {
    public static void main(String[] args) throws InterruptedException {
//        WaitTest test = new WaitTest();
//        test.testWait();
//        int i= 0;
//        Integer j = new Integer(0);
//        System.out.println(i==j);
//        System.out.println(j.equals(i));
        long begin = System.currentTimeMillis();

        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");
        Thread t4 = new Thread(new MyRunnable(), "t4");
        Thread t5 = new Thread(new MyRunnable(), "t5");

        List<Thread> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);

        for(int i  = 0; i < list.size(); i++){
            Thread thread = list.get(i);
            thread.start();
        }

        for(Thread thread: list){
            thread.join();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - begin);

    }

}


