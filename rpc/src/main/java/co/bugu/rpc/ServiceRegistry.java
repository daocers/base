package co.bugu.rpc;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by user on 2017/5/27.
 */
public class ServiceRegistry {
    private static Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    private CountDownLatch latch = new CountDownLatch(1);
    
    private String registryAddress;
    
    public ServiceRegistry(String registryAddress){
        this.registryAddress = registryAddress;
    }
    
    public void register(String data){
        if(data != null){
            ZooKeeper zk = connectServer();
            if(zk != null){
                createNode(zk, data);
            }
        }
    }

    private void createNode(ZooKeeper zk, String data) {
        try{
            byte[] bytes = data.getBytes();
            String path = zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("create zookeeper node ({} => {})", path, data);
        }catch (Exception e){
            logger.error("创建节点失败", e);
        }
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try{
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        latch.countDown();
                    }
                }
            });
            latch.await();
        }catch (Exception e){
            logger.error("zookeeper 连接失败", e);
        }
        return zk;
    }
}
