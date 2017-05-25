package co.bugu.tes.controller;

import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.framework.util.EncryptUtil;
import co.bugu.framework.util.ExcelUtilNew;
import co.bugu.framework.util.JsonUtil;
import co.bugu.tes.enums.ExamStatus;
import co.bugu.tes.enums.UserStatus;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.*;
import co.bugu.tes.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
    IRoleService roleService;
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
     * @param username  查询数据
     * @param name      查询数据
     * @param curPage   当前页码，从1开始
     * @param showCount 当前页码显示数目
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String username, String name, Integer curPage, Integer showCount, ModelMap model) {
        try {
            User user = new User();
            if (StringUtils.isNotEmpty(username)) {
                user.setUsername(username);
            }
            if (StringUtils.isNotEmpty(name)) {
                user.setName(name);
            }
            PageInfo<User> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = userService.findByObject(user, pageInfo);
            model.put("pi", pageInfo);
            model.put("user", user);

            List<String> checkedRole = new ArrayList<>();
            for(User u: pageInfo.getData()){
                List<Role> list = roleService.selectRoleByUser(u.getId());
                StringBuffer buffer = new StringBuffer();
                for(Role role: list){
                    buffer.append(role.getName()).append(" ");
                }
                checkedRole.add(buffer.toString());
            }
            model.put("checkedRole", checkedRole);

            List<Department> departments = departmentService.findByObject(null);
            List<Branch> branches = branchService.findByObject(null);
            List<Station> stations = stationService.findByObject(null);
            Map<Integer, String> departmentMap = new HashMap<>();
            Map<Integer, String> branchMap = new HashMap<>();
            Map<Integer, String> stationMap = new HashMap<>();
            for (Department department : departments) {
                departmentMap.put(department.getId(), department.getName());
            }
            for (Branch branch : branches) {
                branchMap.put(branch.getId(), branch.getName());
            }
            for (Station station : stations) {
                stationMap.put(station.getId(), station.getName());
            }
            model.put("departmentMap", departmentMap);
            model.put("branchMap", branchMap);
            model.put("stationMap", stationMap);
            List<Role> roleList = roleService.findByObject(null);
            model.put("roleList", roleList);
            Map<Integer, String> roleInfoMap = new HashMap<>();
            for(Role role: roleList){
                roleInfoMap.put(role.getId(), role.getName());
            }
            model.put("roleInfoMap", JSON.toJSONString(roleInfoMap));
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
            if (id != null) {
                User user = userService.findById(id);
                if (user != null) {
                    Profile profile = new Profile();
                    profile.setUserId(user.getId());
                    List<Profile> list = profileService.findByObject(profile);
                    if (list.size() != 1) {
                        logger.error("数据紊乱， userId: {}", id);
                        model.put("err", "数据有误，请联系管理员处理");
                    } else {
                        user.setProfile(list.get(0));
                    }
                    if(user.getBranchId() != null){
                        Branch branch = branchService.findById(user.getBranchId());
                        model.put("branchName", branch.getName());
                    }
                }
                model.put("user", user);

            }

            List<Department> departmentList = departmentService.findByObject(null);
            List<Station> stationList = stationService.findByObject(null);
//            List<Branch> branchList = branchService.findByObject(null);
            model.put("departmentList", departmentList);
            model.put("stationList", stationList);

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
    public String save(User user, ModelMap model, RedirectAttributes redirectAttributes) {
        try {
            if (user.getProfile() != null) {
                user.setName(user.getProfile().getName());
            }
            if (user.getId() == null) {
                user.setStatus(0);
                user.setSalt(Constant.DEFALUT_SALT);
                user.setPassword(EncryptUtil.md5(Constant.DEFAULT_PASSWORD + Constant.DEFALUT_SALT));
                userService.save(user);
            } else {
                userService.updateById(user);
            }
        } catch (Exception e) {
            logger.error("保存失败", e);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("err", "保存用户失败");
//            model.put("user", user);
            model.put("err", "保存失败");
            return "redirect:edit.do";
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
            for (User u : list) {
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
        user.setName(profile.getName());
        user.setStatus(UserStatus.NEEDINFO.getStatus());
        String salt = EncryptUtil.getSalt(6);
        user.setSalt(salt);
        String finalPass = EncryptUtil.md5(user.getPassword() + salt);
        user.setPassword(finalPass);
        userService.save(user);
        redirectAttributes.addAttribute("id", user.getId());
        return "redirect:/login.do";
    }

    @RequestMapping("/batchAdd")
    public String batchAdd(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            String fileName = file.getOriginalFilename();
            File tarFile = new File(fileName);
            file.transferTo(tarFile);
            List<List<String>> data = ExcelUtilNew.getData(tarFile);
            tarFile.delete();

            logger.debug("数据： {}", data);
            Map<String, String> branchMap = branchService.getBranchNameIdMap();
            Map<String, String> departmentMap = departmentService.getDepartmentNameIdMap();
            Map<String, String> stationMap = stationService.getStationMap();

            data.remove(0);//删除标题

            List<User> userList = new ArrayList<>();
            for (List<String> line : data) {
                String username = line.get(0);
                String name = line.get(1);
                String idNo = line.get(2);
                String branchName = branchMap.get(line.get(3));
                String departName = departmentMap.get(line.get(4));
                String stationName = stationMap.get(line.get(5));
                User user = new User();
                user.setUsername(username);
                user.setName(name);
                user.setStatus(0);

                user.setSalt(Constant.DEFALUT_SALT);
                user.setPassword(EncryptUtil.md5(Constant.DEFAULT_PASSWORD + Constant.DEFALUT_SALT));
                user.setDepartmentId(StringUtils.isEmpty(departName) ? null : Integer.parseInt(departName));
                user.setStationId(StringUtils.isEmpty(stationName) ? null : Integer.parseInt(stationName));
                user.setBranchId(StringUtils.isEmpty(branchName) ? null : Integer.parseInt(branchName));
                Profile profile = new Profile();
                profile.setIdNo(idNo);
                user.setProfile(profile);
                userList.add(user);
            }
            userService.batchAdd(userList);
            redirectAttributes.addFlashAttribute("msg", "批量导入用户成功");
        } catch (Exception e) {
            logger.error("批量导入用户失败", e);
            redirectAttributes.addFlashAttribute("err", "批量导入用户失败");
        }

        return "redirect:list.do";
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] arrs = new String[]{"用户名（员工号）", "姓名", "身份证号", "机构", "部门", "岗位"};
            ExcelUtilNew.download(request, response, "用户模板", Arrays.asList(arrs), null);
        } catch (Exception e) {
            logger.error("下载用户模板失败", e);
        }
    }


    /**
     * 根据结构id选择考试人员
     *
     * @param branchInfo
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public String getUserForScene(String branchInfo) {
        List<User> res = new ArrayList<>();
        List<Integer> branchIds = JSON.parseArray(branchInfo, Integer.class);
        for (Integer id : branchIds) {
            User user = new User();
            user.setBranchId(id);
            List<User> users = userService.findByObject(user);
            res.addAll(users);
        }
        return JSON.toJSONString(res);
    }


    @RequestMapping("/toChangePassword")
    public String toChangePassword() {
        return "user/changePassword";
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(String oldPassword, String password, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) BuguWebUtil.getUserId(request);
        User user = userService.findById(userId);
        if (EncryptUtil.md5(oldPassword + user.getSalt()).equals(user.getPassword())) {
            String salt = EncryptUtil.getSalt(5);
            String newPassword = EncryptUtil.md5(password + salt);
            user.setSalt(salt);
            user.setPassword(newPassword);
            userService.updateById(user);
            json.put("code", 0);
            BuguWebUtil.remove(request, Constant.SESSION_USER_ID);
        } else {
            json.put("code", -1);
            json.put("err", "旧密码错误");
        }
        return json.toJSONString();

    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(Integer userId, ModelMap model) {
        User user = new User();
        user.setId(userId);
        user.setSalt(Constant.DEFALUT_SALT);
        user.setPassword(EncryptUtil.md5(Constant.DEFAULT_PASSWORD + Constant.DEFALUT_SALT));
        userService.updateById(user);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        return json.toJSONString();
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public String getRole(Integer id){
        JSONObject res = new JSONObject();
        try{
            User user = userService.findFullById(id);
            List<Role> hasRole = user.getRoleList();
            List<Integer> has = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(hasRole)){
                for(Role role: hasRole){
                    has.add(role.getId());
                }
                res.put("has", has);
            }
            res.put("code", 0);
        }catch (Exception e){
            logger.error("获取用户角色信息失败", e);
            res.put("code", -1);
            res.put("err", "获取用户角色信息失败");
        }
        return res.toJSONString();
    }

    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public String setRole(Integer id, String roleId){
        JSONObject res = new JSONObject();
        try{
//            User user = userService.findFullById(id);
            userService.updateRole(id, JSON.parseArray(roleId, Integer.class));
            res.put("code", 0);
        }catch (Exception e){
            logger.error("设置用户角色失败", e);
            res.put("code", -1);
            res.put("err", "设置用户角色失败");
        }
        return res.toJSONString();
    }


}
