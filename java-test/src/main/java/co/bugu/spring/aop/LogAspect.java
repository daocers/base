package co.bugu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by daocers on 2017/6/2.
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(co.bugu.spring.aop.Action)")
    public void annotationPointCut(){};

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截" + action.name());

    }

//    @Before("execution(* co.bugu.spring.aop.DemoMethodService.*(..))")
    @Before("execution(* co.bugu.spring.aop.DemoMethodService.*(..))")
    public void before(){
        System.out.println("good");
//        MethodSignature signature = (MethodSignature) joinpoint.getStaticPart();
//        Method method = signature.getMethod();
//        System.out.println("方法规则式拦截：" + method.getName());
    }
}
