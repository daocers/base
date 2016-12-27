package co.bugu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daocers on 2016/9/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    /**
     * 表名
     * @return
     */
    String value() default "";

    /**
     * 注释信息
     * 对表进行说明
     * @return
     */
    String comment() default "";

}
