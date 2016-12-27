package co.bugu.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by daocers on 2016/10/26.
 */
public class CGLibDynamicProxy implements MethodInterceptor {
    private static CGLibDynamicProxy proxy = new CGLibDynamicProxy();

    public CGLibDynamicProxy(){

    }

    public static CGLibDynamicProxy getInstance(){
        return proxy;
    }

    public <T> T getProxy(Class<T> clazz){
        return (T)Enhancer.create(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object res = methodProxy.invokeSuper(o, objects);
        after();
        return res;
    }

    private void after() {
        System.out.println("cglib after");

    }

    private void before() {
        System.out.println("cglib before");


    }


}
