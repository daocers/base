package co.bugu.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by daocers on 2017/6/2.
 */
@Configuration
@ComponentScan("co.bugu.spring.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
