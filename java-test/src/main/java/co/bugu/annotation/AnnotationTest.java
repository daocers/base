package co.bugu.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by daocers on 2017/3/12.
 */
public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        House house = new House();
        Class clazz = house.getClass();
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation annotation: annotations){
            Method method = annotation.annotationType().getDeclaredMethod("value", null);
            String val = (String) method.invoke(annotation, null);
            System.out.println("类的注解为：" + annotation.annotationType().getName() + ", 值为： " + val);
        }
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            Annotation[] annotations1 = method.getDeclaredAnnotations();
            for(Annotation annotation: annotations1){
                Method m = annotation.annotationType().getDeclaredMethod("value", null);
                String val = (String) m.invoke(annotation, null);
                System.out.println("当前方法：" + method.getName() + "，注解的值为： " + val);
            }
        }
    }

}
