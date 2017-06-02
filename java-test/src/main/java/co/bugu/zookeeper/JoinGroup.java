package co.bugu.zookeeper;

import jdk.nashorn.internal.scripts.JO;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by user on 2017/6/2.
 */
public class JoinGroup extends ConnectionWatcher {
    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;

        String createPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("created " + createPath);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("localhost:2181");
        joinGroup.join("group1", "mem1");
        Thread.sleep(Long.MAX_VALUE);
    }
}
