package co.bugu.threadLearn;


/**
 * Created by daocers on 2016/11/5.
 */
public class MyThread7 extends Thread {
    public MyThread7(){
        System.out.println("countOperate   begin");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
    }
}
