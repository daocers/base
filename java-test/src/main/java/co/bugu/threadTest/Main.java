package co.bugu.threadTest;

/**
 * Created by daocers on 2016/11/3.
 */
public class Main {
    public static void main(String[] args){
        Server server = new Server();
        for(int i = 0; i < 100; i++){
            Task task = new Task("TASK " + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
