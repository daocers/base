package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/4.
 */
public class MyThread3 extends Thread {
    private int count = 5;
    public MyThread3(String name){
        super();
        this.setName(name);
    }
    @Override
    public void run(){
        super.run();
        while(count > 0){
            count--;
            System.out.println("由 " + this.currentThread().getName() + "计算， count = " + count);
        }
    }

    public static void main(String[] args){
        MyThread3 a = new MyThread3("a");
        MyThread3 b = new MyThread3("b");
        MyThread3 c = new MyThread3("c");
        a.start();
        b.start();
        c.start();
    }
}
