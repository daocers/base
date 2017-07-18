package co.bugu.monitor.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by QDHL on 2017/7/18.
 */
public class ExceptionHandler implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args,
                              Object target, RuntimeException throwable){
        System.out.println("产生异常的方法名称为：" + method.getName());
        for(Object arg: args){
            System.out.println("方法的参数：" + arg.toString());
        }
        System.out.println("代理对象：" + target.getClass().getName());
        System.out.println("抛出的异常" + throwable.getMessage()  + ">>>>>>"
         + throwable.getCause());
        System.out.println("异常详细信息：" + throwable.fillInStackTrace());

    }
}
