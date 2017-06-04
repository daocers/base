package co.bugu.spring.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by daocers on 2017/6/4.
 */
@Configuration
@ComponentScan("co.bugu.spring.schedule")
@EnableScheduling
public class TaskSchedulerConfig {
}
