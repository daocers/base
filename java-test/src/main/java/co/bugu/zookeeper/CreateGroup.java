package co.bugu.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by daocers on 2017/6/1.
 */
public class CreateGroup implements Watcher{
    private static final int SESSION_TIMEOUT = 5000;
    private ZooKeeper zk;

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws InterruptedException, IOException {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }
    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created: " + createPath);
    }

    public void close() throws InterruptedException {
        zk.close();
    }
    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        String host = "localhost:2181";
        String groupName = "group";
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(host);
        createGroup.create(groupName);
        createGroup.close();
    }
}
