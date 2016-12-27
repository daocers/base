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
public @interface Id {
//    /**
//     * 主键 名称，如果为空，使用对应的数据库字段名称作为主键名称
//     * @return
//     */
//    String value() default "";

    /**
     * 是否自增，如果没有特别说明，表示自增
     * @return
     */
    boolean autoIncrement() default true;


}
