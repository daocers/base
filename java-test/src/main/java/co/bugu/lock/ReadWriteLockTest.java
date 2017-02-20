package co.bugu.lock;

import org.apache.poi.ss.formula.functions.T;

import javax.sound.midi.MidiDevice;
import java.util.Random;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by user on 2017/2/20.
 */
public class ReadWriteLockTest {
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private String shareData = "孤独等待中...";

    public void write(String info){
        writeLock.lock();
        System.err.println("ThreadName: "+ Thread.currentThread().getName() + " locking...");
        try{
            shareData = info;
            System.err.println("ThreadName:" + Thread.currentThread().getName() + " 修改为：" + info);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.err.println("ThreadName：" + Thread.currentThread().getName() + " unlock..");
            writeLock.unlock();
        }
    }

    public String read(){
        readLock.lock();
        System.out.println("ThreadName: " + Thread.currentThread().getName() + "  lock...");
        try {
            System.out.println("ThreadName: " + Thread.currentThread().getName() + " 获取为： " + shareData);
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("ThreadName: " + Thread.currentThread().getName() + "unlock...");
            readLock.unlock();
        }
        return shareData;
    }

    public static void main(String[] args){
        final  ReadWriteLockTest test = new ReadWriteLockTest();
        for(int i = 0; i < 50; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.read();
                }
            }, "read-thread " + i).start();
        }

        for(int i = 0; i < 5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.write((new Random()).nextLong() + "");
                }
            }, "write-thread " + i).start();
        }
    }

}
