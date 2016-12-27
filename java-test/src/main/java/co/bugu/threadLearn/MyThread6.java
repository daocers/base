package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/5.
 */
public class MyThread6 extends Thread {

    public MyThread6(){
        System.out.println("构造方法的打印：" + Thread.currentThread().getName());
    }
    @Override
    public void run(){
        System.out.println("run方法的打印： " + Thread.currentThread().getName());
    }

    public static void main(String[] args){
        MyThread6 myThread6 = new MyThread6();
        myThread6.start();
//        myThread6.run();
    }
}
