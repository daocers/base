package co.bugu.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.UUID;

/**
 * Created by user on 2017/5/19.
 */
public class ZookeeperReadWriteLock {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =  CuratorFrameworkFactory.newClient("192.168.1.103:2181", retryPolicy);
        client.start();

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, "/read-write-lock");
        final InterProcessMutex readLock = readWriteLock.readLock();
        final InterProcessMutex writeLock = readWriteLock.writeLock();
        try {
            readLock.acquire();
            System.out.println(Thread.currentThread() + "获取到读锁");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        writeLock.acquire();
                        System.out.println(Thread.currentThread() + "获取到写锁");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            writeLock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            Thread.sleep(3000);
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            readLock.release();
        }

        Thread.sleep(100000);
        client.close();
    }
}
