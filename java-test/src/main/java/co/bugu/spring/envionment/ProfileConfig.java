package co.bugu.spring.envionment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by daocers on 2017/6/3.
 */
@Configuration
public class ProfileConfig {
    @Bean
    @Profile("dev")
    public DemoService devDemoService(){
        return new DemoService("from development profile");
    }

    @Bean
    @Profile("prod")
    public DemoService prodDemoService(){
        return new DemoService("from production profile");
    }
}
