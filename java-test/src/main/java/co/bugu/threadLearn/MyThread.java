package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/4.
 */
public class MyThread extends Thread {
    @Override
    public void run(){
        try{
            for(int i = 0; i < 10; i++){
                int time = (int)(Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        MyThread thread = new MyThread();
//        thread.start();
//        System.out.println("运行结束");

        MyThread thread = new MyThread();
        thread.setName("myThread");
        thread.start();

        for(int i = 0; i < 10; i++){
            try {
                int time = (int)(Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
