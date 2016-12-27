package co.bugu.wechat.init;

import co.bugu.framework.core.util.BuguPropertiesUtil;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.io.IOException;

/**
 * Created by daocers on 2016/7/28.
 */
public class ConfiguartionInitListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        try {
            BuguPropertiesUtil.load("conf/wechat.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
