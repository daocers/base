package co.bugu.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2017/6/2.
 */
public class DeleteGroup extends ConnectionWatcher {

    public void delete(String groupName){
        String path = "/" + groupName;
        try{
            List<String> children = zk.getChildren(path, false);
            for(String child: children){
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("127.0.0.1:2181");
        deleteGroup.delete("group1");
        deleteGroup.close();
    }
}
