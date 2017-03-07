package co.bugu.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by user on 2017/3/7.
 */
public class Client {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("已经触发了" + watchedEvent.getType() + "事件");
            }
        });

        zk.create("/testRootPath", "testRootData".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath", false, null)));

        System.out.println(zk.getChildren("/testRootPath", true));


        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zk.getChildren("/testRootPath", true));

        zk.setData("/testRootPath/testChildPathOne", "hahahha".getBytes(),  -1);

        byte[] datas = zk.getData("/testRootPath/testChildPathOne", true, null);

        String str = new String(datas, "utf-8");
        System.out.println(str);


        zk.delete("/testRootPath/testChildPathOne", -1);
        System.out.println(zk.getChildren("/testRootPath", true));
        System.out.println(str);

    }
}
