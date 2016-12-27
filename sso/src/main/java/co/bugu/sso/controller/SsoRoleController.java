package co.bugu.sso.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import co.bugu.framework.core.dao.PageInfo;
import co.bugu.sso.model.SsoRole;
import co.bugu.sso.service.ISsoRoleService;


@Controller
@RequestMapping("/mall/ssoRole")
public class SsoRoleController {

    private static Logger logger = LoggerFactory.getLogger(SsoRoleController.class);

	@Autowired
	ISsoRoleService ssoRoleService;

	@RequestMapping("/list")
	public String list(SsoRole ssoRole, ModelMap model, Integer page) {


		try{
			PageInfo<SsoRole> pageInfo = ssoRoleService.listByObject(ssoRole, page);
    		List<SsoRole> list = pageInfo.getData();
        	model.put("ssoRole", list);
		}catch (Exception e){
			logger.error("[获取列表] 失败", e);
		}
		return "/sso_role/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String toAdd(SsoRole ssoRole, ModelMap model) {
		return "/sso_role/add";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(SsoRole ssoRole, ModelMap model) {
		try {
			ssoRoleService.save(ssoRole);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_role/add";
	}

	@RequestMapping(value = "/edit",method={RequestMethod.GET})
	public String toEdit(SsoRole ssoRole, ModelMap model){
		ssoRole = ssoRoleService.selectById(ssoRole.getId());
		model.put("ssoRole", ssoRole);
		return "/sso_role/edit";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(SsoRole ssoRole, ModelMap model) {
		try {
			ssoRoleService.update(ssoRole);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_role/edit";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(SsoRole ssoRole){
		try{
			ssoRoleService.deleteById(ssoRole.getId());
		} catch (Exception e){
			logger.error("[删除] 失败， id：{}", ssoRole.getId(), e);
		}
		return "/sso_role/list";
	}


}
