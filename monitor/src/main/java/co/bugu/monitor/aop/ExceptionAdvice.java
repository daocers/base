package co.bugu.monitor.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by QDHL on 2017/7/18.
 */
public class ExceptionAdvice implements ThrowsAdvice {
//    private void doThrowing(Joinpoint joinpoint, Throwable ex){
//        System.out.println("-----doThrowing().invoke-----");
//        System.out.println(" 错误信息："+ex.getMessage());
//        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
//        System.out.println(" 可通过joinPoint来获取所需要的内容");
//        System.out.println("-----End of doThrowing()------");
//    }
//
//    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException throwable){
//        System.out.println("产生异常的方法名称：  " + method.getName());
//
//        for(Object o:args){
//            System.out.println("方法的参数：   " + o.toString());
//        }
//
//        System.out.println("代理对象：   " + target.getClass().getName());
//        System.out.println("抛出的异常:    " + throwable.getMessage()+">>>>>>>"
//                + throwable.getCause());
//        System.out.println("异常详细信息：　　　"+throwable.fillInStackTrace());
//    }

    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String signature = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        System.out.println(e.toString());
        System.out.println(e.fillInStackTrace());
        System.out.println("oh, no, error occurring...");
    }


}
