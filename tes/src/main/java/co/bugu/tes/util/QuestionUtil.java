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
     * @param count
     * @return
     */
    public static Set<Integer> getResult(List<Integer> questionIdList, Integer count) {
        Integer max = questionIdList.size();
        Set<Integer> res = new HashSet<>();
        if(count == questionIdList.size()){
            Collections.shuffle(questionIdList);
            for(Integer id: questionIdList){
                res.add(id);
            }
            return res;
        }
        Random random = new Random();
        while(res.size() < count){
            res.add(questionIdList.get(random.nextInt(max)));
        }
        return res;
    }
}
