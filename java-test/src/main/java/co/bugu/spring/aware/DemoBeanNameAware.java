package co.bugu.spring.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by daocers on 2017/6/4.
 */
public class DemoBeanNameAware implements BeanNameAware {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = beanName;
    }
}
