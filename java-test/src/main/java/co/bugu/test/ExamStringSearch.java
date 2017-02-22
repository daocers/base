package co.bugu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/2/22.
 */
public class ExamStringSearch {
    static char[][] data;
    static Map<String, Character> noMap = new HashMap<>();
    static Map<Character, List<String>> charNo = new HashMap<>();

    public boolean find(List<String> list, String target) {
        noMap = new HashMap<>();
        charNo = new HashMap<>();
        List<String> noUsed = new ArrayList<>();
        data = new char[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            data[i] = str.toCharArray();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                String no = i + "-" + j;
                noMap.put(no, c);
                if (charNo.containsKey(c)) {
                    charNo.get(c).add(no);
                } else {
                    List<String> noList = new ArrayList<>();
                    noList.add(no);
                    charNo.put(c, noList);
                }
            }
        }

        char c = target.charAt(0);
        if (charNo.containsKey(c)) {
            List<String> noList = charNo.get(c);
            boolean res = false;
            for (String no : noList) {
                List<String> nolist1 = new ArrayList<>();
                nolist1.add(no);
                List<String> used = new ArrayList<>();
                List<Integer> flag = new ArrayList<>();
                lookfor(nolist1, 0, target, used, flag);
                boolean result = flag.size() > 0;
                if (result == true) {
                    res = true;
                }
            }
            return res;
        } else {
            return false;
        }

    }

    /**
     * @param matchNode 发现当前字符的node集合
     * @param curIndex  当前字符所在的index
     * @param target    目标字符串
     * @param used      已经使用过的node
     * @return
     */
    private void lookfor(List<String> matchNode, Integer curIndex, String target, List<String> used, List<Integer> flag) {
        if (matchNode.size() == 0) {
        } else {
            if (curIndex == target.length() - 1) {
                flag.add(0);
            } else {
                used.addAll(matchNode);
                char nextChar = target.charAt(++curIndex);
                for (String node : matchNode) {
                    List<String> nextMatchNodes = getMatchNode(node, nextChar, used);
                    lookfor(nextMatchNodes, curIndex, target, used, flag);
                }
            }
        }
    }

    /**
     * 获取下一个字符匹配的节点信息
     * @param node
     * @param nextChar
     * @param used
     * @return
     */
    private List<String> getMatchNode(String node, char nextChar, List<String> used) {
        List<String> newNodes = new ArrayList<>();
        String[] nos = node.split("-");
        int i = Integer.valueOf(nos[0]);
        int j = Integer.valueOf(nos[1]);
        Character top = null, bottom = null, left = null, right = null;
        try {
            top = data[i - 1][j];
            String key = (i - 1) + "-" + j;
            if (!used.contains(key) && top == nextChar) {
                newNodes.add(key);
            }
        } catch (Exception e) {

        }
        try {
            bottom = data[i + 1][j];
            String key = (i + 1) + "-" + j;
            if (!used.contains(key) && bottom == nextChar) {
                newNodes.add(key);
            }

        } catch (Exception e) {

        }
        try {
            left = data[i][j - 1];
            String key = i + "-" + (j - 1);
            if (!used.contains(key) && left == nextChar) {
                newNodes.add(key);
            }

        } catch (Exception e) {

        }
        try {
            right = data[i][j + 1];
            String key = i + "-" + (j + 1);
            if (!used.contains(key) && right == nextChar) {
                newNodes.add(key);
            }
        } catch (Exception e) {

        }
        return newNodes;
    }


    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        board.add("ABCE");
        board.add("SFCS");
        board.add("ADEE");
        ExamStringSearch stringSearch = new ExamStringSearch();
        boolean res = stringSearch.find(board, "ABCCEDAS");
        System.out.print("结果为：" + res);
    }
}
