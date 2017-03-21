package co.bugu.tes.controller;

import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.framework.util.JsonUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.*;
import co.bugu.tes.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/scene")
public class SceneController {
    @Autowired
    ISceneService sceneService;

    @Autowired
    IPaperService paperService;

    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IBranchService branchService;
    @Autowired
    IStationService stationService;

    @Autowired
    IQuestionBankService bankService;


    @Autowired
    IPaperPolicyService paperPolicyService;

    @Autowired
    IQuestionMetaInfoService questionMetaInfoService;
    @Autowired
    IQuestionPolicyService questionPolicyService;

    @Autowired
    IUserService userService;

    private static Logger logger = LoggerFactory.getLogger(SceneController.class);

    /**
     * 列表，分页显示
     *
     * @param scene     查询数据
     * @param curPage   当前页码，从1开始
     * @param showCount 当前页码显示数目
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Scene scene, Integer curPage, Integer showCount, ModelMap model, HttpServletRequest request) {
        try {
            Integer userId = (Integer) BuguWebUtil.getUserId(request);
//            if(userId == null){
//                throw new TesException("用户信息获取异常");
//            }
//            scene.setJoinUser(userId + "");
            PageInfo<Scene> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = sceneService.findByObject(scene, pageInfo);
            model.put("pi", pageInfo);
            model.put("scene", scene);
        } catch (Exception e) {
            logger.error("获取列表失败", e);
            model.put("err", "获取列表失败");
        }
        return "scene/list";

    }

    /**
     * 查询数据后跳转到对应的编辑页面
     *
     * @param id    查询数据，一般查找id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEdit(Integer id, ModelMap model) {
        try {
//            是否需要验证当前用户所属的部门，机构，岗位信息非常必要
            Scene scene = null;
            if (id == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

                scene = new Scene();
            } else {
                scene = sceneService.findById(id);
            }
            model.put("scene", scene);
        } catch (Exception e) {
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "scene/edit";
    }

    /**
     * 保存设置，跳转到选择用户界面
     *
     * @param scene
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSceneThenSelectUser(HttpServletRequest request, ModelMap model, Scene scene, RedirectAttributes redirectAttributes) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(scene.getBeginTime());
            calendar.add(Calendar.MINUTE, scene.getDelay());
            calendar.add(Calendar.MINUTE, scene.getDuration());
            scene.setEndTime(calendar.getTime());
            scene.setCreateUserId((Integer) BuguWebUtil.getUserId(request));
            scene.setCreateTime(new Date());
            if (scene.getChangePaper() == null) {
                scene.setChangePaper(1);//为空的时候表示不可更换试卷
            }
            //设置创建的用户id
            scene.setCreateUserId(1);
            scene.setCode("20170101121200");
            if (scene.getId() == null) {
                sceneService.save(scene);
            } else {
                sceneService.updateById(scene);
            }
//            redirectAttributes.addFlashAttribute("scene", scene);
            redirectAttributes.addFlashAttribute(scene);
        } catch (Exception e) {
            logger.error("保存信息失败", e);
            model.put("errMsg", "保存信息失败");
        }
        return "redirect:setUser.do?id=" + scene.getId();
    }

    @RequestMapping(value = "/setUser")
    public String toSetUser(ModelMap model, RedirectAttributes redirectAttributes) {
        Scene scene = (Scene) model.get("scene");
        scene = sceneService.findById(scene.getId());
        List<Branch> branchList = branchService.findByObject(null);
        JSONArray array = new JSONArray();
        for (Branch branch : branchList) {
            JSONObject json = new JSONObject();
            json.put("id", branch.getId());
            json.put("pId", branch.getSuperiorId());
            json.put("name", branch.getName());
            array.add(json);
        }
        model.put("data", array.toString());
        model.put("scene", scene);
        return "scene/setUser";
    }

    /**
     * @param scene
     * @param ids                id集合
     * @param type               0 根据机构选择； 1 直接选择用户
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public String saveUserThenSelectPaperPolciy(Scene scene, String ids, Integer type, RedirectAttributes redirectAttributes) {
        List<Integer> list = JSON.parseArray(ids, Integer.class);
        if (list == null || list.size() == 0) {
            scene = sceneService.findById(scene.getId());
            if (StringUtils.isEmpty(scene.getChoiceInfo())) {
                redirectAttributes.addFlashAttribute("err", "请选择本场考试用户");
//            return  "redirect:selectUser.do";
                return "-1";
            }
        } else {
            if (type == 0) {//根据机构
                scene.setChoiceInfo(JSON.toJSONString(list));
                List<Integer> res = new ArrayList<>();
                for (Integer branchId : list) {
                    User user = new User();
                    user.setBranchId(branchId);
                    List<User> users = userService.findByObject(user);
                    for (User u : users) {
                        res.add(u.getId());
                    }
                }
                list = res;
            } else if (type == 1) {//直接保存用户

            } else {
                redirectAttributes.addFlashAttribute("err", "选择考生方式有误");
                return "-1";
            }
            sceneService.addUserToScene(list, scene);
            scene.setUserType(type);
            Collections.sort(list);
            scene.setJoinUser(JSON.toJSONString(list, true));
            sceneService.updateById(scene);
        }

        redirectAttributes.addFlashAttribute("scene", scene);
        return "0";
    }

    @RequestMapping(value = "/selectPolicy")
    public String toGenPaper(ModelMap model, Scene scene) {
        scene = sceneService.findById(scene.getId());
        if (scene.getPaperPolicyId() != null) {
            PaperPolicy paperPolicy = paperPolicyService.findById(scene.getPaperPolicyId());
            model.put("policyName", paperPolicy.getName());
        }

        //查询全部可用的paperPolicy
        List<PaperPolicy> policyList = paperPolicyService.findByObject(null);
        model.put("policyList", policyList);
        model.put("scene", scene);
        List<Department> departmentList = departmentService.findByObject(null);
        List<Branch> branchList = branchService.findByObject(null);
        List<Station> stationList = stationService.findByObject(null);
        List<QuestionBank> bankList = bankService.findByObject(null);
        model.put("departmentList", departmentList);
        model.put("branchList", branchList);
        model.put("stationList", stationList);
        model.put("bankList", bankList);
        return "scene/selectPolicy";
    }


    /**
     * 保存试题策略
     * 如果是更改，paperPolicyId有值
     * 如果没有做更改， paperPolicyId无值
     *
     * @param scene
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/savePolicy", method = RequestMethod.POST)
    public String savePolicyThenPriview(Scene scene, RedirectAttributes redirectAttributes) {
        Integer paperPolicyId = scene.getPaperPolicyId();
        scene = sceneService.findById(scene.getId());
        if (scene.getPaperPolicyId() != null) {
            PaperPolicy policy = paperPolicyService.findById(scene.getPaperPolicyId());
            String content = policy.getContent();
            if (StringUtils.isNotEmpty(content)) {
                StringBuilder stringBuilder = new StringBuilder();
                com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(content);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject data = (JSONObject) jsonArray.get(i);
                    Integer questionMetaInfoId = data.getInteger("questionMetaInfoId");
                    Integer questionPolicyId = data.getInteger("questionPolicyId");
                    double score = data.getShort("score");
                    QuestionMetaInfo questionMetaInfo = questionMetaInfoService.findById(questionMetaInfoId);
                    QuestionPolicy questionPolicy = questionPolicyService.findById(questionPolicyId);
                    stringBuilder.append(questionMetaInfo.getName())
                            .append(": 试题策略 ")
                            .append(questionPolicy.getName())
                            .append(", ")
                            .append("共 ")
                            .append(questionPolicy.getCount())
                            .append(" 题, 每题 ")
                            .append(score)
                            .append(" 分\n\t");
                }
                redirectAttributes.addFlashAttribute("policyInfo", stringBuilder.toString());
            }
        }

        if (scene.getPaperPolicyId() == null && paperPolicyId == null) {
            redirectAttributes.addFlashAttribute("err", "请选择试卷策略");
            redirectAttributes.addFlashAttribute(scene);
            return "redirect:selectPolicy.do";
        }
        scene.setPaperPolicyId(paperPolicyId);
        scene.setStatus(Constant.STATUS_ENABLE);
        sceneService.updateById(scene);
        redirectAttributes.addFlashAttribute(scene);
        return "redirect:preview.do";
    }

    @RequestMapping(value = "/preview")
    public String toPreview() {
        return "scene/preview";
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public String commit(Scene scene) {
        return "scene/list";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancel(Scene scene) {
        return "scene/list";
    }

    /**
     * 保存本场考试用户信息，并生成试卷，然后跳转到预览页面
     *
     * @param scene
     * @param model
     * @return
     */
    @RequestMapping(value = "/generatePaper", method = RequestMethod.POST)
    public String saveUserAndGeneratePaper(Scene scene, ModelMap model, @ModelAttribute("scene") Scene old) {
        try {


            paperService.generateAllPaper(scene);
            scene = sceneService.findById(scene.getId());
            model.put("scene", scene);
        } catch (Exception e) {
            logger.error("保存用户，生成试卷失败", e);
            model.put("errMsg", "保存信息失败");
        }
        return "scene/preview";
    }

    /**
     * 确认开场
     *
     * @param scene
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(Scene scene) {
        try {


            scene.setStatus(Constant.STATUS_ENABLE);
            sceneService.updateById(scene);
            logger.info("开场成功");
        } catch (Exception e) {
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
     *
     * @param scene 查询条件
     * @return
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(Scene scene) {
        try {
            List<Scene> list = sceneService.findByObject(scene);
            return JsonUtil.toJsonString(list);
        } catch (Exception e) {
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
     * 异步请求 删除
     *
     * @param scene id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Scene scene) {
        try {
            sceneService.delete(scene);
            return "0";
        } catch (Exception e) {
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
