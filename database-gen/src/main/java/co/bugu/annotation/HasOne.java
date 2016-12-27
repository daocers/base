package co.bugu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daocers on 2016/9/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface HasOne {
    /**
     * 关联字段的数据库列属性
     * 如果不设置或者为空串，使用驼峰转下划线的方式修改
     * @return
     */
    String value() default "";

    /**
     * 是否转交控制权给owner
     * 如果转交，将不再给对应的被控制放进行  关联查询owner的方法
     * @return
     */
    boolean reverse() default false;
}
