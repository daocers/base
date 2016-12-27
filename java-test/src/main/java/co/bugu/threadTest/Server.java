package co.bugu.threadTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by daocers on 2016/11/3.
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server(){
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(Task task){
        System.out.println("Server: a new task has arrived! ");
        executor.execute(task);

        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());

        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());

        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());

    }

    public void endServer(){
        executor.shutdown();
    }
}
