package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.model.Paper;
import co.bugu.tes.model.Scene;
import co.bugu.tes.service.IPaperService;
import co.bugu.tes.service.ISceneService;
import com.sun.javafx.tk.TKSceneListener;
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
import java.util.List;

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
        if(scene.getId() == null){
            throw new Exception("非法操作");
        }
        Integer userId = (Integer) BuguWebUtil.getUserId(request);
        Paper  paper = new Paper();
        paper.setUserId(userId);
        paper.setSceneId(scene.getId());
        paper.setStatus(0);//0 正常， 1未作答， 2 作废
        List<Paper> papers = paperService.findByObject(paper);
        if(papers == null || papers.size() > 0){
            model.put("err", "没有找到试卷，请联系管理员！");
        }else{
            model.put("paper", papers.get(0));
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


}
