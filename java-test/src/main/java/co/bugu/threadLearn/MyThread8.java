package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/21.
 */
public class MyThread8 extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            System.out.println("i==" + (i + 1));
        }
    }

    public static void main(String[] args){
        try {
            MyThread8 thread8 = new MyThread8();
            thread8.start();
            Thread.sleep(1000);
            thread8.interrupt();
            Thread.currentThread().interrupt();
            System.out.println("是否停止1 ？ =" + thread8.interrupted());
            System.out.println("是否停止2 ？ =" + Thread.interrupted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
