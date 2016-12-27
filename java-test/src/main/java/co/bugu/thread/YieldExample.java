package co.bugu.thread;

/**
 * Created by daocers on 2016/10/27.
 */
public class YieldExample {
    public static void main(String[] args){
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY);
        consumer.setPriority(Thread.MAX_PRIORITY);

        producer.start();
        consumer.start();

//        Thread product1 = new Producer();
//        product1.start();
    }


}


class Producer extends Thread{
    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println("I am producer:  item " + i);
            Thread.yield();
        }

    }
}


class Consumer extends Thread{
    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println("I am consumer: item " + i);
            Thread.yield();
        }
    }
}