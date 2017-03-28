package co.bugu.tes.controller;

import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.framework.util.JedisUtil;
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
            User user = userService.findFullById(userId);
            List<Role> roleList = user.getRoleList();
            if(roleList == null){
                throw new Exception("该用户没有分配角色");
            }
            List<String> roles = new ArrayList<>();
            for(Role role: roleList){
                roles.add(role.getCode());
            }
            if(roles.contains("admin")){//超级管理员，选择全部

            }else if(roles.contains("branchManager")){//分行管理员

            }else if(roles.contains("user")){//一般用户
                JedisUtil.get(Constant.USER_SCENE_LIST + userId);
            }
            Paper paper = new Paper();
            paper.setStatus(0);
            paper.setUserId((Integer) BuguWebUtil.getUserId(request));
            List<Paper> paperList = paperService.findByObject(paper);
            List<Integer> sceneIdList = new ArrayList<>();
            if(paperList != null && paperList.size() > 0){
                Integer sceneId = paper.getSceneId();
                if(!sceneIdList.contains(sceneId)){
                    sceneIdList.add(sceneId);
                }
            }
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveScene(HttpServletRequest request, Scene scene) {
        JSONObject json = new JSONObject();
        try {
            json.put("code", 0);
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(scene.getBeginTime());
            calendar.add(Calendar.MINUTE, scene.getDelay());
            calendar.add(Calendar.MINUTE, scene.getDuration());
            scene.setEndTime(calendar.getTime());
            scene.setCreateUserId((Integer) BuguWebUtil.getUserId(request));
            scene.setCreateTime(now);
            if (scene.getChangePaper() == null) {
                scene.setChangePaper(1);//为空的时候表示不可更换试卷
            }
            Integer userId = (Integer) BuguWebUtil.getUserId(request);
            if(userId != null){
                User user = userService.findById(userId);
                scene.setBranchId(user.getBranchId());
                scene.setDepartmentId(user.getDepartmentId());
                scene.setCreateUserId(userId);
            }else{
                throw new Exception("用户未登录");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String index = JedisUtil.incr(Constant.SCENE_INDEX) + "";
            JedisUtil.expire(Constant.SCENE_INDEX, 60);
            //设置创建的用户id
            scene.setCode(format.format(now) + index);
            if (scene.getId() == null) {
                sceneService.save(scene);
            } else {
                sceneService.updateById(scene);
            }
            json.put("data", scene.getId());
        } catch (Exception e) {
            json.put("code", -1);
            json.put("msg", "场次信息保存失败");
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "/setUser")
    public String toSetUser(Integer sceneId, ModelMap model, HttpServletRequest request) {
        Integer userId = (Integer) BuguWebUtil.getUserId(request);
        List<String> role = userService.getRoleList(userId);
        List<Branch> branchList = new ArrayList<>();
        if(role.contains("admin")){
            branchList = branchService.findByObject(null);
        }else if(role.contains("branchManager")){
            User user = userService.findById(userId);
            getAllUnderBranch(user.getBranchId(), branchList);
            branchList.add(branchService.findById(user.getBranchId()));
        }else if(role.contains("user")){

        }

//        Scene scene = (Scene) model.get("scene");
        Scene scene = sceneService.findById(sceneId);

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
     * 获取当前机构下的所有机构信息
     * @param branchId
     * @param branchList
     */
    private void getAllUnderBranch(Integer branchId, List<Branch> branchList){
        Branch obj = new Branch();
        obj.setSuperiorId(branchId);
        List<Branch> branches = branchService.findByObject(obj);
        if(branches == null || branches.size() == 0){
            return;
        }else{
            branchList.addAll(branches);
            for(Branch bran: branches){
                getAllUnderBranch(bran.getId(), branchList);
            }
        }

    }
    /**
     * @param sceneId
     * @param info                id集合
     * @param type               0 根据机构选择； 1 直接选择用户 2 我的机构 3 直接指定授权码
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public String saveUserThenSelectPaperPolciy(Integer sceneId, String info, Integer type) {
        JSONObject json = new JSONObject();
        try{
            if(sceneId == null){
                json.put("code", -1);
                json.put("err", "场次id不能为空");
                return json.toJSONString();
            }

            Scene scene = new Scene();
            scene.setId(sceneId);
            scene.setUserType(type);

            if(type == 0){//根据机构选择
                List<Integer> userIdList = new ArrayList<>();
                if(StringUtils.isEmpty(info)){
                    json.put("code", -1);
                    json.put("err", "没有选择机构");
                    return json.toJSONString();
                }
                List<Integer> branchIdList = JSON.parseArray(info, Integer.class);
                for(Integer branchId: branchIdList){
                    User user = new User();
                    user.setBranchId(branchId);
                    List<User> userList = userService.findByObject(user);
                    for(User u: userList){
                        userIdList.add(u.getId());
                    }
                }
                scene.setJoinUser(JSON.toJSONString(userIdList));
                scene.setChoiceInfo(info);
            }else if(type == 1){//直接指定用户
                scene.setJoinUser(info);
                scene.setChoiceInfo(info);
            }else if(type == 2){//我的机构

            }else if(type == 3){//授权码
                scene.setAuthCode(info);
            }else {
                json.put("code", -1);
                json.put("err", "人员选择方式不支持");
                return json.toJSONString();
            }
            sceneService.updateById(scene);
            json.put("code", 0);
        }catch (Exception e){
            logger.error("设置参考用户失败", e);
            json.put("code", -1);
            json.put("err", "保存考试用户失败");
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "/selectPolicy")
    public String toGenPaper(ModelMap model, Scene scene, HttpServletRequest request) throws Exception {
        scene = sceneService.findById(scene.getId());
        if (scene.getPaperPolicyId() != null) {
            PaperPolicy paperPolicy = paperPolicyService.findById(scene.getPaperPolicyId());
            model.put("policyName", paperPolicy.getName());
        }

        User user = userService.findById((Integer) BuguWebUtil.getUserId(request));
        PaperPolicy obj = new PaperPolicy();
        obj.setBranchId(user.getBranchId());
        obj.setStatus(0);
        PageInfo<PaperPolicy> pageInfo = new PageInfo<>();
        pageInfo = paperPolicyService.findByObject(obj, pageInfo);
        model.put("pageInfo", pageInfo);

        //查询全部可用的paperPolicy
//        List<PaperPolicy> policyList = paperPolicyService.findByObject(null);
//        model.put("policyList", policyList);
        model.put("scene", scene);
//        List<Department> departmentList = departmentService.findByObject(null);
//        List<Branch> branchList = branchService.findByObject(null);
//        List<Station> stationList = stationService.findByObject(null);
        List<QuestionBank> bankList = bankService.findByObject(null);
//        model.put("departmentList", departmentList);
//        model.put("branchList", branchList);
//        model.put("stationList", stationList);
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
        Integer bankId = scene.getBankId();
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
        scene.setBankId(bankId);
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
    @ResponseBody
    public String confirm(Scene scene, ModelMap model) {
        JSONObject json = new JSONObject();
        try {
            scene.setStatus(Constant.STATUS_ENABLE);
            sceneService.updateById(scene);
            logger.info("开场成功");
            scene = sceneService.findById(scene.getId());
            try{
                paperService.generateAllPaper(scene);
                json.put("code", 0);
            }catch (Exception e){
                json.put("code", -1);
                json.put("msg", "生成试卷失败");
                logger.error("生成试卷失败", e);
            }
        } catch (Exception e) {
            json.put("code", -1);
            json.put("msg", "确认开场失败");
            logger.error("确认开场失败", e);
        }
        return json.toJSONString();
    }

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
}
