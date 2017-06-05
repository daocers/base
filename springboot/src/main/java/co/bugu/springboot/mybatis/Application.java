package co.bugu.springboot.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * Created by user on 2017/6/2.
 */
@SpringBootApplication
//@ImportResource({"classpath:*.xml"})
//@MapperScan("co.bugu.mapper")
public class Application {

    protected static Logger logger = LoggerFactory.getLogger(Application.class);

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new org.apache.tomcat.jdbc.pool.DataSource();
//    }
//
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:/mybatis/*Mapper.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//        logger.info("spring boot start success");
//    }
}
