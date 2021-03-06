package co.bugu.dubbo.controller;

import co.bugu.dubbo.model.SystemParam;
import co.bugu.dubbo.service.ISystemParamService;
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

import java.util.List;

@Controller
@RequestMapping("/param")
public class SystemParamController {
    @Autowired
    ISystemParamService systemparamService;

    private static Logger logger = LoggerFactory.getLogger(SystemParamController.class);

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        SystemParam param = new SystemParam();
        param.setName("姓名");
        param.setCode("name");
        param.setDescription("简要描述");
        param.setValue("allen");
        param.setValueType(0);
        systemparamService.save(param);

        List<SystemParam> list = systemparamService.findAllByObject(new SystemParam());
        return JsonUtil.toJsonString(list);

    }
    /**
    * 列表，分页显示
    * @param systemparam  查询数据
    * @param curPage 当前页码，从1开始
    * @param showCount 当前页码显示数目
    * @param model
    * @return
    */
    @RequestMapping(value = "/list")
    public String list(SystemParam systemparam, Integer curPage, Integer showCount, ModelMap model){
        try{
            PageInfo<SystemParam> pageInfo = new PageInfo<>(showCount, curPage);
            pageInfo = systemparamService.listByObject(systemparam, pageInfo);
            model.put("pi", pageInfo);
            model.put("systemparam", systemparam);
        }catch (Exception e){
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "systemparam/list";

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
            SystemParam systemparam = systemparamService.findById(id);
            model.put("systemparam", systemparam);
        }catch (Exception e){
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "systemparam/edit";
    }

    /**
    * 保存结果，根据是否带有id来表示更新或者新增
    * @param systemparam
    * @param model
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SystemParam systemparam, ModelMap model){
        try{
            systemparamService.saveOrUpdate(systemparam);
        }catch (Exception e){
            logger.error("保存失败", e);
            model.put("systemparam", systemparam);
            model.put("errMsg", "保存失败");
            return "systemparam/edit";
        }
        return "redirect:list.do";
    }

    /**
    * 异步请求 获取全部
    * @param systemparam 查询条件
    * @return
    */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(SystemParam systemparam){
        try{
            List<SystemParam> list = systemparamService.findAllByObject(systemparam);
            return JsonUtil.toJsonString(list);
        }catch (Exception e){
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
    * 异步请求 删除
    * @param systemparam id
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SystemParam systemparam){
        try{
            systemparamService.delete(systemparam);
            return "0";
        }catch (Exception e){
            logger.error("删除失败", e);
            return "-1";
        }
    }
}
