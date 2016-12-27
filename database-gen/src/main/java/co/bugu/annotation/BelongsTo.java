package co.bugu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daocers on 2016/9/27.
 * 一个实例归属于另一个实例
 * 可以决定将控制权转移到归属方
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BelongsTo {
    /**
     * 记录对应归属的
     * @return
     */
    String value() default "";

    /**
     * 归属的类的名称
     */
    Class owner();

}
