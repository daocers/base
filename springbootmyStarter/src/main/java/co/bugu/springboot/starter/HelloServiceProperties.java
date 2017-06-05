package co.bugu.springboot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by user on 2017/6/5.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
    private static final String MSG = "world";

    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
