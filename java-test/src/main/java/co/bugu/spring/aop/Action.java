package co.bugu.spring.aop;

import java.lang.annotation.*;

/**
 * Created by daocers on 2017/6/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
