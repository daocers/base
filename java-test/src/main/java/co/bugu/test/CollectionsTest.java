package co.bugu.test;

import java.util.*;

/**
 * Created by daocers on 2017/2/24.
 */
public class CollectionsTest {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        list.removeAll(set);

        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer num = iterator.next();
            System.out.println("num:" + num);
            iterator.remove();
        }
        System.out.println("list size: " + list.size());
    }
}
