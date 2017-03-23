package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.model.*;
import co.bugu.tes.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/1/12.
 * 考试类
 * 处理考试
 */
@Controller
@RequestMapping("/exam")
public class ExamController {
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ISceneService sceneService;
    @Autowired
    IPaperService paperService;
    @Autowired
    IQuestionService questionService;
    @Autowired
    IQuestionMetaInfoService metaInfoService;
    @Autowired
    IAnswerService answerService;

    /**
     *
     * @param model
     * @param type type= new,待参加考试， type= his, 已参加考试
     * @return
     */
    @RequestMapping("/{type}/list")
    public String list(ModelMap model, @PathVariable String type){
        String page = "";
        List<Scene> sceneList = new ArrayList<>();
        if("new".equals(type)){
            sceneList = sceneService.findByObject(null);
            page = "exam/list";
        }else if("his".equals(type)){
            sceneList = sceneService.findByObject(null);
            page = "exam/history";
        }
        model.put("sceneList", sceneList);
        return page;
    }

    /**
     * 跳转考试须知界面
     * @param scene
     * @return
     */
    @RequestMapping("/note")
    public String toNote(Scene scene){
        return "exam/note";
    }


    @RequestMapping("/exam")
    public String toExam(Scene scene, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer, String> metaInfoMap = new HashMap<>();
        List<QuestionMetaInfo> metaInfoList = metaInfoService.findByObject(null);
        for(QuestionMetaInfo metaInfo: metaInfoList){
            metaInfoMap.put(metaInfo.getId(), metaInfo.getCode());
        }
        model.put("metaInfo", JSON.toJSONString(metaInfoMap));
        if(scene.getId() == null){
            throw new Exception("非法操作");
        }
        Integer userId = (Integer) BuguWebUtil.getUserId(request);
        Paper  paper = new Paper();
        paper.setUserId(userId);
        paper.setSceneId(scene.getId());
        paper.setStatus(0);//0 正常， 1未作答， 2 作废
        List<Paper> papers = paperService.findByObject(paper);
        if(papers == null || papers.size() == 0){
            model.put("err", "没有找到试卷，请联系管理员！");
        }else{
            model.put("paper", papers.get(0));
            scene = sceneService.findById(scene.getId());
            model.put("scene", scene);
        }
        return "exam/exam";
    }

    @RequestMapping("/answer")
    @ResponseBody
    public String commitQuestion(Integer paperId, Integer questionId, String answer){
        return null;
    }

    @RequestMapping("/commit")
    public String commitPaper(){
        return "exam/result";
    }


    @RequestMapping("/getQuestion")
    @ResponseBody
    public String getQuestionAndSaveAnswer(Integer paperId, Integer current, Integer currentIndex, Integer questionId, Integer time, String answer){
        JSONObject json = new JSONObject();
        json.put("code", 0);
        try{
            if(currentIndex == 0 && answer.equals("-1") ){
                logger.info("获取第一题");
            }else if(current > 0 && StringUtils.isNotEmpty(answer)){
                Answer a = new Answer();
                a.setAnswer(answer);
                a.setPaperId(paperId);
                a.setQuestionId(current);
                a.setSeconds(time);
                answerService.save(a);
                logger.info("保存作答记录成功");
            }

            if(questionId == -1){
                logger.info("已经是最后一题了");
                json.put("code", -2);
                json.put("msg", "已经是最后一题了");
            }else{
                Question question = questionService.findById(questionId);
                if(question == null){
                    json.put("code", -1);
                    json.put("msg", "没有查到对应的试题");
                }else{
                    JSONObject ques = new JSONObject();
                    ques.put("title", question.getTitle());
                    ques.put("content", question.getContent());
                    ques.put("metaInfoId", question.getMetaInfoId());
                    ques.put("remark", question.getExtraInfo());
                    json.put("data", ques);
                }
            }
        }catch (Exception e){
            logger.error("获取试题信息失败", e);
            json.put("code", -1);
            json.put("msg", "获取试题信息失败");
        }
        return json.toJSONString();
    }


}
