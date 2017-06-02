package co.bugu.spring.annotation1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by user on 2017/6/2.
 */
@Configuration
public class JavaConfig {
    @Bean
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService service = new UseFunctionService();
        service.setService(functionService());
        return service;
    }

    @Bean
    public UseFunctionService useFunctionService(FunctionService service){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setService(service);
        return useFunctionService;
    }
}
