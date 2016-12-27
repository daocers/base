package com.daocers.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 复合索引
 * Created by daocers on 2016/9/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CompIndex {
    /**
     * 该字段在复合索引中的序号
     * 如果都没有，按照字段出现的先后属性处理
     * @return
     */
    int order() default 0;
}
