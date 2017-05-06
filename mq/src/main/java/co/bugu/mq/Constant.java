package co.bugu.mq;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2017/5/6.
 */
public class Constant {
    private static List<String> sendList = new ArrayList<>();

    public static Set<String> getConsumeSet() {
        return consumeSet;
    }

    public static void setConsumeSet(Set<String> consumeSet) {
        Constant.consumeSet = consumeSet;
    }

    private static Set<String> consumeSet = new HashSet<>();

    public static List<String> getSendList() {
        return sendList;
    }

    public static void setSendList(List<String> sendList) {
        Constant.sendList = sendList;
    }
}
