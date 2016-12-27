package co.bugu.sso.controller;

import java.util.List;

import co.bugu.sso.service.ISsoPermissionService;
import co.bugu.sso.model.SsoPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import co.bugu.framework.core.dao.PageInfo;


@Controller
@RequestMapping("/mall/ssoPermission")
public class SsoPermissionController {

    private static Logger logger = LoggerFactory.getLogger(SsoPermissionController.class);

	@Autowired
	ISsoPermissionService ssoPermissionService;

	@RequestMapping("/list")
	public String list(SsoPermission ssoPermission, ModelMap model, Integer page) {


		try{
			PageInfo<SsoPermission> pageInfo = ssoPermissionService.listByObject(ssoPermission, page);
    		List<SsoPermission> list = pageInfo.getData();
        	model.put("ssoPermission", list);
		}catch (Exception e){
			logger.error("[获取列表] 失败", e);
		}
		return "/sso_permission/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String toAdd(SsoPermission ssoPermission, ModelMap model) {
		return "/sso_permission/add";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(SsoPermission ssoPermission, ModelMap model) {
		try {
			ssoPermissionService.save(ssoPermission);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_permission/add";
	}

	@RequestMapping(value = "/edit",method={RequestMethod.GET})
	public String toEdit(SsoPermission ssoPermission, ModelMap model){
		ssoPermission = ssoPermissionService.selectById(ssoPermission.getId());
		model.put("ssoPermission", ssoPermission);
		return "/sso_permission/edit";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(SsoPermission ssoPermission, ModelMap model) {
		try {
			ssoPermissionService.update(ssoPermission);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_permission/edit";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(SsoPermission ssoPermission){
		try{
			ssoPermissionService.deleteById(ssoPermission.getId());
		} catch (Exception e){
			logger.error("[删除] 失败， id：{}", ssoPermission.getId(), e);
		}
		return "/sso_permission/list";
	}


}
