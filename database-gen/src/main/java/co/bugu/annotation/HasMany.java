package co.bugu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一对多关联
 * 当前对象拥有多个标记的字段
 * Created by daocers on 2016/9/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface HasMany {
    /**
     * 关联表的key
     * @return
     */
    String key() default "";

    /**
     * 关联的属性的列的名称，
     * 如果不设置或者默认为空，使用驼峰转下划线的方式处理
     * @return
     */
    String column() default "";

    /**
     * 关联表名称
     * @return
     */
    String table() default "";
    /**
     * 该字段是否排序
     * 默认为不排序
     * @return
     */
    boolean sort() default false;

    /**
     * 是否需要连接表
     * @return
     */
    boolean connTable() default  true;
}
