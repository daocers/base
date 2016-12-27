package co.bugu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daocers on 2016/9/24.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    /**
     * 字段名称
     * @return
     */
    String value() default "";

    /**
     * 字段类型
     * @return
     */
    String type() default "";

    /**
     * 字段长度
     * @return
     */
    String length() default "";

    /**
     * 注释
     * @return
     */
    String comment() default "";
}
