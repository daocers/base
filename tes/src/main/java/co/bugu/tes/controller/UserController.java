package co.bugu.tes.controller;

import co.bugu.framework.util.ExcelUtilNew;
import co.bugu.tes.model.Branch;
import co.bugu.tes.model.Department;
import co.bugu.tes.model.Station;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IBranchService;
import co.bugu.tes.service.IDepartmentService;
import co.bugu.tes.service.IStationService;
import co.bugu.tes.service.IUserService;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IBranchService branchService;
    @Autowired
    IStationService stationService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
    * 列表，分页显示
    * @param user  查询数据
    * @param curPage 当前页码，从1开始
    * @param showCount 当前页码显示数目
    * @param model
    * @return
    */
    @RequestMapping(value = "/list")
    public String list(User user, Integer curPage, Integer showCount, ModelMap model){
        try{
            PageInfo<User> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = userService.findByObject(user, pageInfo);
            model.put("pi", pageInfo);
            model.put("user", user);
        }catch (Exception e){
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "user/list";

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
            User user = userService.findById(id);
            model.put("user", user);
            List<Department> departmentList = departmentService.findByObject(null);
            List<Station> stationList = stationService.findByObject(null);
            List<Branch> branchList = branchService.findByObject(null);
            model.put("departmentList", departmentList);
            model.put("stationList", stationList);
            model.put("branchList", branchList);
        }catch (Exception e){
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "user/edit";
    }

    /**
    * 保存结果，根据是否带有id来表示更新或者新增
    * @param user
    * @param model
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user, ModelMap model){
        try{
            if(user.getId() == null){
                userService.save(user);
            }else{
                userService.updateById(user);
            }
        }catch (Exception e){
            logger.error("保存失败", e);
            model.put("user", user);
            model.put("errMsg", "保存失败");
            return "user/edit";
        }
        return "redirect:list.do";
    }

    /**
    * 异步请求 获取全部
    * @param user 查询条件
    * @return
    */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(User user){
        try{
            List<User> list = userService.findByObject(user);
            return JsonUtil.toJsonString(list);
        }catch (Exception e){
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
    * 异步请求 删除
    * @param user id
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(User user){
        try{
            userService.delete(user);
            return "0";
        }catch (Exception e){
            logger.error("删除失败", e);
            return "-1";
        }
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> title = new ArrayList<>();
            title = Arrays.asList(new String[]{"项目", "电话"});
            List<List> content = new ArrayList<>();
            content.add(title);
            content.add(title);
            response.setContentType("application/x-msdownload;");

            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            String time=formatter.format(new Date());
            response.setHeader("Content-disposition", "attachment; filename=" + new String(("内部产品" +time + ".xlsx").getBytes("utf-8"), "ISO8859-1"));
            OutputStream outputStream = response.getOutputStream();
            ExcelUtilNew.writeToOutputStream("xlsx", title, content, outputStream);
            outputStream.close();
        } catch (Exception e) {

        }
    }
}
