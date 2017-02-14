package co.bugu.tes.util;

import co.bugu.framework.util.JedisUtil;
import co.bugu.framework.util.exception.TesException;
import co.bugu.tes.global.Constant;

import java.util.*;

/**
 * 试题辅助类
 * 用于获取指定属性的试题 的数量或者指定属性的试题的id信息
 * Created by daocers on 2017/2/13.
 */
public class QuestionUtil {
    /**
     * 根据题型id，属性id组合获取符合条件的试题的id
     *
     * @param questionMetaInfoId
     * @param ids                属性组合
     * @return
     * @throws TesException
     */
    public static Set<String> findQuestionByPropItemId(Integer questionMetaInfoId, List<Integer> ids) throws TesException {
        String[] keys = new String[ids.size()];
//        for(int i = 0; i < ids.length; i++){
//            keys[i] = Constant.QUESTION_PROPITEM_ID + questionMetaInfoId + "_"+ ids[i];
//        }
        for (int i = 0; i < ids.size(); i++) {
            keys[i] = Constant.QUESTION_PROPITEM_ID + questionMetaInfoId + "_" + ids.get(i);
        }
        return JedisUtil.sinterForObj(keys);
    }

    /**
     * 根据题型id，属性id组合获取符合条件的试题的数量
     *
     * @param questionMetaInfoId
     * @param ids
     * @return
     * @throws TesException
     */
    public static int getCountByPropItemId(Integer questionMetaInfoId, List<Integer> ids) throws TesException {
        String[] keys = new String[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            keys[i] = Constant.QUESTION_PROPITEM_ID + questionMetaInfoId + "_" + ids.get(i);
        }
//        for(int i = 0; i < ids.length; i++){
//            keys[i] = Constant.QUESTION_PROPITEM_ID +questionMetaInfoId + "_" + ids[i];
//        }
        return JedisUtil.sinterForSize(keys);
    }

    /**
     * 根据数量获取最终的试题id组合
     * @param questionIdList
     * @param count  需要选择的数量
     * @return
     */
    public static Set<Integer> getResult(List<Integer> questionIdList, Integer count) throws Exception {
        if(count > questionIdList.size()){
            throw new Exception("需要选择的数量大于试题集合的数量");
        }
        Set<Integer> res = new HashSet<>();
        Collections.shuffle(questionIdList);
        for(int i = 0; i < count; i++){
            res.add(questionIdList.get(i));
        }
        return res;
    }

    /**
     * 根据试题类型随机获取一定数量的试题
     * @param questionMetaInfoId  试题类型id
     * @param count 需要的数量
     * @return
     */
    public static List<Integer> getResultByQuesMetaId(Integer questionMetaInfoId, Integer count) {
        return null;
    }
}
