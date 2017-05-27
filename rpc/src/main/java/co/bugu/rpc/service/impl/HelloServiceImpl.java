package co.bugu.rpc.service.impl;

import co.bugu.rpc.HelloService;

/**
 * Created by user on 2017/5/27.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello! " + name;
    }
}
