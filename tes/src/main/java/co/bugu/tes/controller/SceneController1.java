package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.model.Page;
import co.bugu.tes.model.Paper;
import co.bugu.tes.model.Scene;
import co.bugu.tes.service.ISceneService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/scene1")
public class SceneController1 {
    private static Logger logger = LoggerFactory.getLogger(SceneController1.class);

    @Autowired
    ISceneService sceneService;

    /**
     * 我开场的
     * @return
     */
    @RequestMapping("/list")
    public String list(String type, ModelMap model, HttpServletRequest request){
        List<Scene> sceneList = new ArrayList<>();
        Integer userId = (Integer) BuguWebUtil.getUserId(request);

        if("open".equals(type)){//我创建的
            Scene scene = new Scene();
            scene.setCreateUserId(userId);
            sceneList = sceneService.findByObject(scene);

        }else if("join".equals(type)){//我已经参加的
            Paper paper = new Paper();
            paper.setUserId(userId);
//            List<Paper> paperList = pap
        }else{
            logger.error("非法参数");

        }
        return "scene/list";
    }




}
