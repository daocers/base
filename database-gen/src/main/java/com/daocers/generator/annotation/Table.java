package com.daocers.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一个类将映射为一张表
 * Created by daocers on 2016/9/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
    /**
     * 表名
     * 默认情况将采用驼峰转下划线命名
     * @return
     */
    String value() default "";
}
