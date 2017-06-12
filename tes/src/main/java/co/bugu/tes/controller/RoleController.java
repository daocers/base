package co.bugu.tes.controller;

import co.bugu.framework.core.dao.PageInfo;
import co.bugu.framework.core.mybatis.SearchParamUtil;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.framework.util.JsonUtil;
import co.bugu.tes.annotation.Menu;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Role;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IRoleService;
import co.bugu.tes.service.IUserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Menu(value = "")
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService roleService;
    @Autowired
    IAuthorityService authorityService;
    @Autowired
    IUserService userService;

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
    * 列表，分页显示
    * @param role  查询数据
    * @param curPage 当前页码，从1开始
    * @param showCount 当前页码显示数目
    * @param model
    * @return
    */
    @Menu(value = "")
    @RequestMapping(value = "/list")
    public String list(Role role, Integer curPage, Integer showCount, ModelMap model, HttpServletRequest request){
        try{
            SearchParamUtil.processSearchParam(role, request);
            PageInfo<Role> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = roleService.findByObject(role, pageInfo);
            model.put("pi", pageInfo);
            model.put("role", role);
        }catch (Exception e){
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "role/list";

    }

    @Menu(value = "")
    @RequestMapping("/index/{type}")
    public String index(@PathVariable String type, ModelMap modelMap, HttpServletRequest request){
        if("student".equals(type)){
            Integer userId = (Integer) BuguWebUtil.getUserId(request);

        }else if("teacher".equals(type)){

        }else if("admin".equals("type")){

        }
        return "index/index";
    }

    /**
    * 查询数据后跳转到对应的编辑页面
    * @param id 查询数据，一般查找id
    * @param model
    * @return
    */
    @Menu(value = "")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEdit(Integer id, ModelMap model){
        try{
            List<Integer> idList = new ArrayList<>();
            Role role = null;
            if(id != null){
                role = roleService.findById(id);
                List<Authority> selectedList = role.getAuthorityList();
                for(Authority auth: selectedList){
                    idList.add(auth.getId());
                }
            }


            List<Authority> authorityList = authorityService.findByObject(null);
            List<Map<String, Object>> data = new ArrayList<>();
            for(Authority auth: authorityList){
                Map<String, Object> map = new HashMap<>();
                map.put("id", auth.getId());
                map.put("pId", auth.getSuperiorId());
                map.put("name", auth.getName());
                if(idList.contains(auth.getId())){
                    map.put("checked", true);
                }
                if(Constant.AUTH_TYPE_BOX.equals(auth.getType())){
                    map.put("open", true);
                }
                data.add(map);
            }
            model.put("zNode", JSON.toJSONString(data, true));
            model.put("role", role);
        }catch (Exception e){
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "role/edit";
    }

    /**
    * 保存结果，根据是否带有id来表示更新或者新增
    * @param role
    * @param model
    * @return
    */
    @Menu(value = "")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Role role, @RequestParam(value = "nodeInfo", required = false) String info, ModelMap model){
        try{
            role.setStatus(Constant.STATUS_ENABLE);
            List<Map<String, Integer>> xList = new ArrayList<>();
            if(StringUtils.isNotEmpty(info)){
                List<Integer> authIds = JSON.parseArray(info, Integer.class);
                for(Integer id: authIds){
                    Map<String, Integer> map = new HashMap<>();
                    map.put("authorityId", id);
                    xList.add(map);
                }
            }
            roleService.save(role, xList);
        }catch (Exception e){
            logger.error("保存失败", e);
            model.put("role", role);
            model.put("zNode", info);
            model.put("errMsg", "保存失败");
            return "redirect:edit.do?id=" + role.getId();
        }
        return "redirect:list.do";
    }

    /**
    * 异步请求 获取全部
    * @param role 查询条件
    * @return
    */
    @Menu(value = "")
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(Role role){
        try{
            List<Role> list = roleService.findByObject(role);
            return JsonUtil.toJsonString(list);
        }catch (Exception e){
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
    * 异步请求 删除
    * @param role id
    * @return
    */
    @Menu(value = "")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Role role){
        try{
            roleService.delete(role);
            return "0";
        }catch (Exception e){
            logger.error("删除失败", e);
            return "-1";
        }
    }
}
