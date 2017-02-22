package co.bugu.test;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by user on 2017/2/22.
 */
public class ExamBomb {

    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        List<Kingdom> kingdoms = new LinkedList<>();
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(100);
            kingdoms.add(num1 <= num2 ? new Kingdom(num1, num2) : new Kingdom(num2, num1));
        }
        System.out.println("王国信息：" + JSON.toJSONString(kingdoms, true));
        ExamBomb examBomb = new ExamBomb();
        int min = examBomb.findMinBomb(kingdoms);
        System.out.println("最小炸弹数量：" + min);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - begin) + "毫秒。");
    }
    /**
     * 根据传入的王国信息获取最少的炸弹数量
     * @param kingdoms
     * @return
     */
    public Integer findMinBomb(List<Kingdom> kingdoms){
        Collections.sort(kingdoms, new Comparator<Kingdom>() {
            @Override
            public int compare(Kingdom k1, Kingdom k2) {
                return k1.getFrom() - k2.getFrom();
            }
        });

        System.out.println("排序后的王国：" + JSON.toJSONString(kingdoms));
        List<Kingdom> res = new ArrayList<>();
        recursion(kingdoms, res);
        System.out.println("炸弹区间：" + JSON.toJSONString(res));
        return res.size();
    }

    /**
     * 递归执行
     *
     * 1如果只有一个区间，返回当前区间
     * 2 如果有两个一下区间，判断
     * 1）如果两个连续，返回第一个区间，列表中删除第一个区间，继续执行
     * 2）如果两个区间连续，取交集，该情况包括交叉连续和包含连续
     * @param kingdoms
     */
    private void recursion(List<Kingdom> kingdoms, List<Kingdom> res){
        if(kingdoms.size()  == 1){
            res.add(kingdoms.get(0));
        }if(kingdoms.size() > 1){
            Kingdom k1 = kingdoms.get(0);
            Kingdom k2 = kingdoms.get(1);
            if(k1.getTo() < k2.getFrom()){//没交集
                res.add(k1);
                kingdoms.remove(0);

            }else{//有交集，返回交集
                int to1 = k1.getTo();
                int to2 = k2.getTo();
                int from = k2.getFrom();
                kingdoms.remove(0);
                kingdoms.remove(0);
                kingdoms.add(0, new Kingdom(from, to1 <= to2 ? to1 : to2));
            }
            recursion(kingdoms, res);
        }
    }



}

class Kingdom{
    int from;
    int to;

    public Kingdom(int from , int to){
        this.from = from;
        this.to = to;
    }
    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

}
