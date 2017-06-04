package co.bugu.spring.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daocers on 2017/6/4.
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("每隔五秒钟执行一次 " + format.format(new Date()));
    }

    @Scheduled(cron = "0 35 16 ? * *")
    public void fixTimeExecution(){
        System.out.println("在指定时间 " + format.format(new Date()) + " 执行");
    }
}
