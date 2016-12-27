package co.bugu.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by daocers on 2016/10/19.
 */
public class ThreadPool {
    private static int worker_num = 5;
    private WorkThread[] workThreads;
    private static volatile int finished_task = 0;
    private List<Runnable> taskQueue = new LinkedList<>();
    private static ThreadPool threadPool;

    private ThreadPool() {
        this(5);
    }

    private ThreadPool(int worker_num) {
        ThreadPool.worker_num = worker_num;
        workThreads = new WorkThread[worker_num];
        for (int i = 0; i < worker_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    public static ThreadPool getThreadPool() {
        return getThreadPool(ThreadPool.worker_num);
    }

    public static ThreadPool getThreadPool(int worker_num) {
        if (worker_num <= 0) {
            worker_num = ThreadPool.worker_num;
        }
        if (threadPool == null) {
            threadPool = new ThreadPool(worker_num);
        }
        return threadPool;
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void execute(Runnable[] task) {
        synchronized (taskQueue) {
            for (Runnable runnable : task) {
                taskQueue.add(runnable);
            }
            taskQueue.notify();
        }
    }

    public void execute(List<Runnable> task) {
        synchronized (taskQueue) {
            for (Runnable t : task) {
                taskQueue.add(t);
            }
            taskQueue.notify();
        }
    }

    public void destory() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < worker_num; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;
        }
        threadPool = null;
        taskQueue.clear();
    }

    public int getWorkThreadNumber() {
        return worker_num;
    }

    public int getFinishedTaskNumber() {
        return finished_task;
    }

    public int getWaitTaskNumber() {
        return taskQueue.size();
    }

    @Override
    public String toString() {
        return "WorkThread number:" + worker_num + "  finished task number:"
                + finished_task + "  wait task number:" + getWaitTaskNumber();
    }

    private class WorkThread extends Thread {
        private boolean isRunning = true;

        public void run() {
            Runnable r = null;
            while (isRunning) {
                synchronized (taskQueue) {
                    while(isRunning && taskQueue.isEmpty()){
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!taskQueue.isEmpty()){
                        r = taskQueue.remove(0);
                    }
                }
                if(r != null){
                    r.run();
                }
                finished_task++;
                r = null;
            }
        }

        public void stopWorker(){
            isRunning = false;
        }
    }
}
