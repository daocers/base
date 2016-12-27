package com.daocers.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 一对多，或者一对一关联的时候，使用关联表
 * Created by daocers on 2016/9/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JoinTable {
    /**
     * 关联表名
     * @return
     */
    String name() default "";

    /**
     * 关联表的key
     * @return
     */
    String key() default "";


    /**
     * 关联表的被关联的一方的key
     * @return
     */
    String column() default "";

    /**
     * 是否排序
     * @return
     */
    boolean sort() default false;
}
