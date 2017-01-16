package co.bugu.tes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2017/1/12.
 * 考试类
 * 处理考试
 */
@Controller
@RequestMapping("/exam")
public class ExamController {
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);

    /**
     * 检查考试状态，包括参考人员的状态， 未入场，已入场，已提交等
     * @return
     */
    @RequestMapping("/checkStatus")
    public String checkStatus(){
        try{

        }catch (Exception e){
            logger.error("获取考试状态失败", e);
        }
        return null;
    }

    /**
     * 提交答案
     * 记录提交的时间，便于中途断电等异常情况下进行再次答题而时间不错
     * @param answer
     * @param questionId
     * @return
     */
    public String commit(String answer, Integer questionId){
        try{

        }catch (Exception e){
            logger.error("提交试题答案失败", e);
        }
        return null;
    }

    @RequestMapping("/edit")
    public String toEdit(){
        return "exam/edit";
    }
}
