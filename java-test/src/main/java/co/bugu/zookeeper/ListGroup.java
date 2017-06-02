package co.bugu.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2017/6/2.
 */
public class ListGroup extends ConnectionWatcher {
    public void list(String groupName){
        String path = "/" + groupName;
        try{
            List<String> children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.println("No members in group : " + groupName);
                System.exit(1);
            }
            for(String child: children){
                System.out.println(child);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("127.0.0.1:2181");
        listGroup.list("group1");
        listGroup.close();
    }
}
