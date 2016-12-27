package co.bugu.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daocers on 2016/10/26.
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> container = new HashMap<Thread, T>();

    public void set(T val){
        container.put(Thread.currentThread(), val);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T val = container.get(thread);
        if(val == null && !container.containsKey(thread)){
            val = initialValue();
            container.put(thread, val);
        }
        return val;
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    protected T initialValue(){
        return null;
    }
}
