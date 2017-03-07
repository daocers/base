package co.bugu.dubbo.service.impl;

import co.bugu.dubbo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2016/10/12.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.1")
public class UserService implements IUserService {
    @Override
    public String getName() {
        return "allen";
    }
}
