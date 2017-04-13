package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.model.*;
import co.bugu.tes.service.*;
import co.bugu.websocket.WebSocketSessionUtil;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
    @RequestMapping("/list")
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

    @RequestMapping("/index")
    public String index(){
        return "exam/index";
    }


    /**
     * 跳转考试须知界面
     * @param
     * @return
     */
    @RequestMapping("/note")
    public String toNote(String authCode, ModelMap model){
        Scene scene = new Scene();
        scene.setAuthCode(authCode);
        List<Scene> sceneList = sceneService.findByObject(scene);
        if(sceneList != null && sceneList.size() == 1){
            scene = sceneList.get(0);
            model.put("scene", scene);
        }else{
            if(sceneList == null || sceneList.size() == 0){
                model.put("err", "没有找到对应的场次");
            }else{
                model.put("err", "场次编码重复，请联系管理员");
            }
            return "exam/index";
        }
        return "exam/note";
    }


    /**
     * 跳转到考试界面
     * @param scene
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/exam")
    public String toExam(Scene scene, ModelMap model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        Map<Integer, QuestionMetaInfo> metaInfoMap = new HashMap<>();
        List<QuestionMetaInfo> metaInfoList = metaInfoService.findByObject(null);
        for(QuestionMetaInfo metaInfo: metaInfoList){
            metaInfoMap.put(metaInfo.getId(), metaInfo);
        }
        model.put("metaInfo", JSON.toJSONString(metaInfoMap));
        if(scene.getId() == null){
            throw new Exception("非法操作,没有找到对应的场次");
        }

        boolean continueFlag = true;
        scene = sceneService.findById(scene.getId());
        Date now = new Date();
        Date beginTime = scene.getBeginTime();
        Date endTime = scene.getEndTime();
        if(beginTime.compareTo(now) > 0){
            redirectAttributes.addFlashAttribute("msg", "考试开始时间未到，请等待。");
            continueFlag = false;
        }else{
            Integer delay = scene.getDelay();
            if(delay == null || delay == 0){//不考虑递延状态，考试时间内都可以进入
                if(endTime.compareTo(now) <= 0){
                    redirectAttributes.addFlashAttribute("msg", "考试已经结束，无法参加考试。");
                    continueFlag = false;
                }
            }else{
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(beginTime);
                calendar.add(Calendar.MINUTE, delay);
                Date lastEntry = calendar.getTime();
                if(lastEntry.compareTo(now) < 0){
                    continueFlag = false;
                    redirectAttributes.addFlashAttribute("msg", "开场超过" + delay + "分钟，无法参加考试");
                }
            }
        }


        //不符合考试条件，跳转到考试列表页面
        if(continueFlag == false){
            return "redirect:index.do";
        }

        Integer leftMinute = 0;
        Long left = (endTime.getTime() - now.getTime())/1000/60;
        if(left.intValue() < scene.getDuration()){
            leftMinute = left.intValue();
        }else{
            leftMinute = scene.getDuration();
        }

        model.put("timeLeft", leftMinute/60 + "h" + leftMinute % 60 + "m" + "0s");


        Integer userId = (Integer) BuguWebUtil.getUserId(request);
        Paper paper = new Paper();
        paper.setUserId(userId);
        paper.setSceneId(scene.getId());
        paper.setStatus(0);
        List<Paper> paperList = paperService.findByObject(paper);
        if(paperList != null && paperList.size() > 0){
            paper = paperList.get(0);
        }else{
            Integer paperId = paperService.generatePaperForUser(scene, userId);
            paper = paperService.findById(paperId);
        }
        scene = sceneService.findById(scene.getId());

//        Paper  paper = new Paper();
//        paper.setUserId(userId);
//        paper.setSceneId(scene.getId());
//        paper.setStatus(0);//0 正常， 1未作答， 2 作废
//        List<Paper> papers = paperService.findByObject(paper);
//        if(papers == null || papers.size() == 0){//没有生成试卷，生成试卷
//            Integer paperId = paperService.generatePaperForUser(scene, (Integer) BuguWebUtil.getUserId(request));
////            model.put("err", "没有找到试卷，请联系管理员！");
//            paper = paperService.findById(paperId);
//        }else{
//            paper = papers.get(0);
////            model.put("paper", papers.get(0));
//
//            model.put("scene", scene);
//        }
        Map<Integer, Question> questionMap = new HashMap<>();
        List<Integer> idList = new ArrayList<>();
        String content = paper.getContent();
        Map map = JSON.parseObject(content, Map.class);
        Iterator<Integer> keyIter = map.keySet().iterator();
        while(keyIter.hasNext()){
            Integer key = keyIter.next();
            List<Integer> ids = (List<Integer>) map.get(key);
            idList.addAll(ids);
        }

        for(Integer id: idList){
            questionMap.put(id, questionService.findById(id));
        }

        Map<Integer, String> answerMap = new HashMap<>();
        Answer obj = new Answer();
        obj.setPaperId(paper.getId());
        List<Answer> answers = answerService.findByObject(obj);
        for(Answer answer: answers){
            answerMap.put(answer.getQuestionId(), answer.getAnswer());
        }

        model.put("answerMap", JSON.toJSONString(answerMap));

        model.put("questionMap", JSON.toJSONString(questionMap));
        model.put("questionIdList", JSON.toJSONString(idList));

        model.put("scene", scene);
        model.put("paper", paper);
        return "exam/examine";
    }


    /**
     * 提交问题答案 单选判断等暂时不需要
     * @param questionId 题目id
     * @param answer    答案
     * @param timeLeft 提交时候剩余时间
     * @param paperId 试卷id
     * @return
     */
    @RequestMapping(value = "/commitQuestion", method = RequestMethod.POST)
    @ResponseBody
    public String commitQuestion(Integer paperId, Integer questionId, String answer, String timeLeft){
        JSONObject json = new JSONObject();
        json.put("code", 0);
        if(StringUtils.isEmpty(answer)){
            json.put("code", -1);
            json.put("msg", "答案为空");
        }else{
            Answer obj = new Answer();
            obj.setPaperId(paperId);
            obj.setQuestionId(questionId);
            List<Answer> answers = answerService.findByObject(obj);
            if(answers != null && answers.size() == 1){
                Answer ans = answers.get(0);
                ans.setAnswer(answer);
                ans.setTimeLeft(timeLeft);
                answerService.updateById(ans);
            }else{
                Answer ans = new Answer();
                ans.setAnswer(answer);
                ans.setPaperId(paperId);
                ans.setQuestionId(questionId);
                ans.setTimeLeft(timeLeft);
                answerService.save(ans);
            }
        }
        return json.toJSONString();
    }


    /**
     * 提交试卷
     * @param paperId
     * @param answerInfo
     * @return
     */
    @RequestMapping(value = "/commitPaper", method = RequestMethod.POST)
    @ResponseBody
    public String commitPaper(Integer paperId, String answerInfo){
        JSONObject json = new JSONObject();
        json.put("code", 0);
        try{
            boolean saveAnsFlag = true;
            try{
                Map<String, String> answerMap = JSON.parseObject(answerInfo, HashMap.class);
                answerService.savePaperAnswer(answerMap, paperId);
            }catch (Exception e){
                json.put("code", 1);
                json.put("msg", "保存试卷答案失败");
                saveAnsFlag = false;
                logger.error("保存试卷答案失败", e);
            }

            Paper paper = paperService.findById(paperId);
            paper.setId(paperId);
            if(saveAnsFlag){
                paper.setStatus(3);//保存成功
            }else{
                paper.setStatus(4);//保存失败
            }
            paperService.updateById(paper);
        }catch (Exception e){
            logger.error("提交试卷信息失败", e);
            json.put("code", -1);
            json.put("msg", "提交试卷失败");
        }
        return json.toJSONString();
    }

    /**
     * 获取试题
     * @param questionId  试题id
     * @return
     */
    @RequestMapping("/getQuestion")
    @ResponseBody
    public String getQuestion(Integer questionId){
        JSONObject json = new JSONObject();
        json.put("code", 0);
        try{
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
        }catch (Exception e){
            logger.error("获取试题信息失败", e);
            json.put("code", -1);
            json.put("msg", "获取试题信息失败");
        }
        return json.toJSONString();
    }


    @ResponseBody
    @RequestMapping("/sendMessage")
    public String sendMessageToClient() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", 4);
        Map<Integer, WebSocketSession> sessionMap = WebSocketSessionUtil.getAllWebSocketSessions();
        for(Map.Entry<Integer, WebSocketSession> entry: sessionMap.entrySet()){
            WebSocketSession session = entry.getValue();
            session.sendMessage(new TextMessage(json.toJSONString()));
        }
        return "";
    }

    /**
     * 向指定客户端发消息
     * @param userId
     * @param message
     * @throws IOException
     */
    private void sendMessageToUserClient(Integer userId, String message) throws IOException {
        WebSocketSession session = WebSocketSessionUtil.getWebSocketSession(userId);
        session.sendMessage(new TextMessage(message));
    }


}
