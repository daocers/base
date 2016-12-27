package co.bugu.syn;

/**
 * Created by daocers on 2016/10/14.
 */
public class SynchronizedTest {
    private static Integer num = 0;

    public synchronized static void methodA(){
        while(num < 50){
            num++;
            System.out.println("methodA: " + num);
        }
    }

    public synchronized static void methodB(){
        while(num < 50){
            num++;
            System.out.println("methodB: " + num);
        }
    }

    public static void main(String[] args){
        SynchronizedTest test = new SynchronizedTest();
        Thread t1 = new Thread(new MyRunner1(test));
        Thread t2 = new Thread(new MyRunner2(test));
        t1.start();
        t2.start();

    }

}

class MyRunner1 implements Runnable{
    private SynchronizedTest synchronizedTest;

    public MyRunner1(SynchronizedTest test){
        this.synchronizedTest = test;
    }

    @Override
    public void run() {
        synchronizedTest.methodB();
    }
}

class MyRunner2 implements Runnable{
    private SynchronizedTest synchronizedTest;

    public MyRunner2(SynchronizedTest test){
        this.synchronizedTest = test;
    }

    @Override
    public void run() {
        synchronizedTest.methodA();
    }
}

