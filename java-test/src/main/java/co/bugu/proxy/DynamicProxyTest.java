package co.bugu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by daocers on 2017/3/10.
 */
public class DynamicProxyTest {
    public static void main(String[]  args){
        IUserService userService = new UserServiceImpl();
        IUserService proxy = (IUserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), new Handler(userService));
        proxy.printName();
    }
}

class Handler implements InvocationHandler{

    IUserService userService;
    public Handler(IUserService userService){
        this.userService = userService;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object res =  method.invoke(userService, args);
        System.out.println("end...");
        return res;
    }
}
