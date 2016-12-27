package co.bugu.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by daocers on 2016/11/3.
 */
public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number){
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if((number == 1) || (number == 0)){
            result = 1;
        }else{
            for(int i = 2; i < number; i++){
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);

        return result;
    }
}
