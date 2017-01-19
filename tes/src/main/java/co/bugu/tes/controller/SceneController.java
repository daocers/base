package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.PaperPolicy;
import co.bugu.tes.model.Scene;
import co.bugu.tes.service.IPaperPolicyService;
import co.bugu.tes.service.IPaperService;
import co.bugu.tes.service.ISceneService;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.util.JsonUtil;
import com.sun.beans.editors.DoubleEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/scene")
public class SceneController {
    @Autowired
    ISceneService sceneService;

    @Autowired
    IPaperService paperService;

    @Autowired
    IPaperPolicyService paperPolicyService;

    private static Logger logger = LoggerFactory.getLogger(SceneController.class);

    /**
    * 列表，分页显示
    * @param scene  查询数据
    * @param curPage 当前页码，从1开始
    * @param showCount 当前页码显示数目
    * @param model
    * @return
    */
    @RequestMapping(value = "/list")
    public String list(Scene scene, Integer curPage, Integer showCount, ModelMap model){
        try{
            PageInfo<Scene> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = sceneService.findByObject(scene, pageInfo);
            model.put("pi", pageInfo);
            model.put("scene", scene);
        }catch (Exception e){
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "scene/list";

    }

    /**
    * 查询数据后跳转到对应的编辑页面
    * @param id 查询数据，一般查找id
    * @param model
    * @return
    */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEdit(Integer id, ModelMap model){
        try{
//            是否需要验证当前用户所属的部门，机构，岗位信息非常必要
            Scene scene = null;
            if(id == null){
                scene = new Scene();
            }else{
                scene = sceneService.findById(id);
            }
            model.put("scene", scene);
        }catch (Exception e){
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "scene/edit";
    }

    /**
     * 保存设置，跳转到选择用户界面
     * @param scene
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSceneThenSelectUser(HttpServletRequest request, ModelMap model, Scene scene, RedirectAttributes redirectAttributes){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(scene.getBeginTime());
            calendar.add(Calendar.MINUTE, scene.getDelay());
            calendar.add(Calendar.MINUTE, scene.getDuration());
            scene.setEndTime(calendar.getTime());
            scene.setCreateUserId((Integer) BuguWebUtil.getUserId(request));
//            if(scene.getId() == null){
//                sceneService.save(scene);
//            }else{
//                sceneService.updateById(scene);
//            }
            redirectAttributes.addFlashAttribute("scene", scene);

        }catch (Exception e){
            logger.error("保存信息失败", e);
            model.put("errMsg", "保存信息失败");
        }
        return "scene/selectUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUserThenSelectPaperPolciy(@ModelAttribute("scene")Scene scene, String userInfos, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("scene", scene);
        return "scene/generatePaper";
    }

    @RequestMapping(value = "/savePolicy", method = RequestMethod.POST)
    public String savePolicyThenPriview(){
        return "scene/preview";
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public String commit(Scene scene){
        return "scene/list";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancel(Scene scene){
        return "scene/list";
    }

    /**
     * 保存本场考试用户信息，并生成试卷，然后跳转到预览页面
     * @param scene
     * @param model
     * @return
     */
    @RequestMapping(value = "/generatePaper", method = RequestMethod.POST)
    public String saveUserAndGeneratePaper(Scene scene, ModelMap model, @ModelAttribute("scene")Scene old){
        try{

            paperService.generateAllPaper(scene);
        }catch (Exception e){
            logger.error("保存用户，生成试卷失败", e);
            model.put("errMsg", "保存信息失败");
        }
        return "scene/preview";
    }

    /**
     * 确认开场
     * @param scene
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(Scene scene){
        try{
            scene.setStatus(Constant.STATUS_ENABLE);
            sceneService.updateById(scene);
            logger.info("开场成功");
        }catch (Exception e){
            logger.error("确认开场失败", e);
        }
        return "redirect:list.do";
    }

//    /**
//    * 保存结果，根据是否带有id来表示更新或者新增
//    * @param scene
//    * @param model
//    * @return
//    */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Scene scene, ModelMap model){
//        try{
//            if(scene.getId() == null){
//                sceneService.save(scene);
//            }else{
//                sceneService.updateById(scene);
//            }
//        }catch (Exception e){
//            logger.error("保存失败", e);
//            model.put("scene", scene);
//            model.put("errMsg", "保存失败");
//            return "scene/edit";
//        }
//        return "redirect:list.do";
//    }

    /**
    * 异步请求 获取全部
    * @param scene 查询条件
    * @return
    */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(Scene scene){
        try{
            List<Scene> list = sceneService.findByObject(scene);
            return JsonUtil.toJsonString(list);
        }catch (Exception e){
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
    * 异步请求 删除
    * @param scene id
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Scene scene){
        try{
            sceneService.delete(scene);
            return "0";
        }catch (Exception e){
            logger.error("删除失败", e);
            return "-1";
        }
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        format.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
//    }
}
