package com.daocers.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 普通字段
 * 如果不标注，按照驼峰转下划线处理
 * Created by daocers on 2016/9/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {
    String name() default "";
    String type() default "";
    int length() default 0;

}
