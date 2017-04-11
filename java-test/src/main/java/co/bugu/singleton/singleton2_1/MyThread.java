package co.bugu.singleton.singleton2_1;

/**
 * Created by user on 2017/4/11.
 */
public class MyThread extends Thread {
    public void run(){
        System.out.println(MyObject.getInstance().hashCode());
    }
}
