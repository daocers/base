package co.bugu.spring.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2017/6/4.
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer num){
        System.out.println("执行异步操作：" + num);
    }


    @Async
    public void executeAsyncTaskPlus(Integer num){
        System.out.println("执行异步操作+1:" + (num + 1));
    }
}
