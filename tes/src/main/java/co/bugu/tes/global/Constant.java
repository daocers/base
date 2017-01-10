package co.bugu.tes.global;

/**
 * Created by daocers on 2016/8/2.
 */
public interface Constant {
    Integer STATUS_ENABLE = 0;
    Integer STATUS_DISABLE = 1;
    Integer STATUS_DELETE = 2;

    Integer AUTH_TYPE_MENU = 0;
    Integer AUTH_TYPE_OPR = 1;
    Integer AUTH_TYPE_BOX = 2;

    Integer AUTH_API_TRUE = 0;
    Integer AUTH_API_FALSE = 1;

    Integer BRANCH_LEVEL_TOP = 0;//总行
    Integer BRANCH_LEVEL_ONE = 1;
    Integer BRANCH_LEVEL_TWO = 2;
    Integer BRANCH_LEVEL_THREE = 3;
    Integer BRANCH_LEVEL_FOUR = 4;

    /*试题答案正误*/
    Integer ANSWER_TRUE = 0;
    Integer ANSWER_FALSE = 1;


    /**
     * 保存指定题型的 对应属性的题目的数量
     * 使用时候后面需要加上题型的id作为key，存储在redis上
     */
    String METAINFO_PROP_COUNT = "MetaInfo_propItem_count_";


    String USER_INFO_PREFIX = "USER_INFO_";

    String SESSION_USER_ID = "user_id";

    String QUESTION_PROPITEM_ID = "QUESTION_PROPITEM_ID_";
}
