package co.bugu.sourcecode.controller;

import co.bugu.framework.util.JsonUtil;
import co.bugu.sourcecode.model.Demo;
import co.bugu.sourcecode.service.IDemoService;
import co.bugu.framework.core.dao.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by daocers on 2016/7/26.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    IDemoService demoService;

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    /**
     * 列表，分页显示
     * @param demo  查询数据
     * @param page 当前页码，从1开始
     * @param showCount 当前页码显示数目
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Demo demo, Integer page, Integer showCount, ModelMap model){
        try{
            PageInfo<Demo> pageInfo = new PageInfo<>(showCount, page);
            pageInfo = demoService.listByObject(demo, pageInfo);
            model.put("pi", pageInfo);
            model.put("demo", demo);
        }catch (Exception e){
            logger.error("获取列表失败", e);
            model.put("errMsg", "获取列表失败");
        }
        return "demo/list";

    }

    /**
     * 查询数据后跳转到对应的编辑页面
     * @param demo 查询数据，一般查找id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEdit(Demo demo, ModelMap model){
        try{
            demo = demoService.findByObject(demo);
            model.put("demo", demo);
        }catch (Exception e){
            logger.error("获取信息失败", e);
            model.put("errMsg", "获取信息失败");
        }
        return "demo/edit";
    }

    /**
     * 保存结果，根据是否带有id来表示更新或者新增
     * @param demo
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Demo demo, ModelMap model){
        try{
            if(demo.getId() == null){
                demoService.save(demo);
            }else{
                demoService.updateById(demo);
            }
        }catch (Exception e){
            logger.error("保存失败", e);
            model.put("demo", demo);
            model.put("errMsg", "保存失败");
            return "demo/edit";
        }
        return "redirect:list";
    }

    /**
     * 异步请求 获取全部
     * @param demo 查询条件
     * @return
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public String listAll(Demo demo){
        try{
            List<Demo> list = demoService.findAllByObject(demo);
            return JsonUtil.toJsonString(list);
        }catch (Exception e){
            logger.error("获取全部列表失败", e);
            return "-1";
        }
    }

    /**
     * 异步请求 删除
     * @param demo id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Demo demo){
        try{
            demoService.delete(demo);
            return "0";
        }catch (Exception e){
            logger.error("删除失败", e);
            return "-1";
        }
    }
}
