package co.bugu.tes.controller;

import co.bugu.framework.util.EncryptUtil;
import co.bugu.framework.util.ExcelUtilNew;
import co.bugu.tes.enums.ExamStatus;
import co.bugu.tes.enums.UserStatus;
import co.bugu.tes.model.*;
import co.bugu.tes.service.*;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.util.JsonUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IBranchService branchService;
    @Autowired
    IStationService stationService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 列表，分页显示
     *
     * @param user      查询数据
     * @param curPage   当前页码，从1开始
     * @param showCount 当前页码显示数目
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(User user, Integer curPage, Integer showCount, ModelMap model) {
        try {
//            Map<String, Object> param = new HashMap<>();
//            param.put("LK_username", "%allen%");
//            param.put("EQ_status", 1);
//            ThreadLocalUtil.set(param);
//            user.setStatus(1);
//            user.setUsername("%allen%");
            PageInfo<User> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = userService.findByObject(user, pageInfo);
            model.put("pi", pageInfo);
            model.put("user", user);
        } catch (Exception e) {
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "user/list";

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
            User user = userService.findById(id);
            if (user != null) {
                Profile profile = new Profile();
                profile.setUserId(user.getId());
                List<Profile> list = profileService.findByObject(profile);
                if (list.size() > 1) {
                    logger.error("数据紊乱， userId: {}", id);
                    model.put("err", "数据有误，请联系管理员处理");
                } else {
                    user.setProfile(list.get(0));
                }
            }
            model.put("user", user);
            List<Department> departmentList = departmentService.findByObject(null);
            List<Station> stationList = stationService.findByObject(null);
            List<Branch> branchList = branchService.findByObject(null);
            model.put("departmentList", departmentList);
            model.put("stationList", stationList);
            model.put("branchList", branchList);
        } catch (Exception e) {
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "user/edit";
    }

    /**
     * 保存结果，根据是否带有id来表示更新或者新增
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user, ModelMap model) {
        try {
            if (user.getId() == null) {
                userService.save(user);
            } else {
                userService.updateById(user);
            }
        } catch (Exception e) {
            logger.error("保存失败", e);
            model.put("user", user);
            model.put("errMsg", "保存失败");
            return "user/edit";
        }
        return "redirect:list.do";
    }

    /**
     * 异步请求 获取全部
     *
     * @param user 查询条件
     * @return
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(User user) {
        try {
            List<User> list = userService.findByObject(user);
            for(User u: list){
                u.setProfile(profileService.findByUserId(u.getId()));
            }
            return JsonUtil.toJsonString(list);
        } catch (Exception e) {
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
     * 异步请求 删除
     *
     * @param user id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(User user) {
        try {
            userService.delete(user);
            return "0";
        } catch (Exception e) {
            logger.error("删除失败", e);
            return "-1";
        }
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "user/register";
    }

    @RequestMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        Profile profile = user.getProfile();
        profile.setExamStatus(ExamStatus.ENABLE.getStatus());
        profile.setExamStatusUpdate(new Date());
        profile.setRegistTime(new Date());
        user.setStatus(UserStatus.NEEDINFO.getStatus());
        String salt = EncryptUtil.getSalt(6);
        user.setSalt(salt);
        String finalPass = EncryptUtil.md5(user.getPassword() + salt);
        user.setPassword(finalPass);
        userService.save(user);
        redirectAttributes.addAttribute("id", user.getId());
        return "redirect:edit.do";
    }

    @RequestMapping("/batchAdd")
    public String batchAdd(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            File tarFile = new File(fileName);
            file.transferTo(tarFile);
            List<List<String>> data = ExcelUtilNew.getData(tarFile);
            logger.error("数据： {}", data);
            tarFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:list.do";
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] arrs = new String[]{"用户名（员工号）", "姓名", "身份证号", "机构", "部门", "岗位"};
            ExcelUtilNew.downloadModel(request, response, "用户模板", Arrays.asList(arrs));
        } catch (Exception e) {
            logger.error("下载用户模板失败", e);
        }
    }


    /**
     * 根据结构id选择考试人员
     * @param branchInfo
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public String getUserForScene(String branchInfo){
        List<User> res = new ArrayList<>();
        List<Integer> branchIds = JSON.parseArray(branchInfo, Integer.class);
        for(Integer id: branchIds){
            User user = new User();
            user.setBranchId(id);
            List<User> users = userService.findByObject(user);
            res.addAll(users);
        }
        return JSON.toJSONString(res);
    }
}
