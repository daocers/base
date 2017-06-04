package co.bugu.spring.bean;

/**
 * Created by daocers on 2017/6/3.
 */
public class BeanWayService {
    public void init(){
        System.out.println("@bean-init-method");
    }

    public BeanWayService(){
        super();
        System.out.println("初始化构造函数-beanwayservice");
    }

    public void destory(){
        System.out.println("@bean-destory-method");
    }
}
