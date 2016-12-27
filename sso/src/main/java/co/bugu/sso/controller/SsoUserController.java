package co.bugu.sso.controller;

import java.util.List;

import co.bugu.sso.model.SsoUser;
import co.bugu.sso.service.ISsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import co.bugu.framework.core.dao.PageInfo;


@Controller
@RequestMapping("/mall/ssoUser")
public class SsoUserController {

    private static Logger logger = LoggerFactory.getLogger(SsoUserController.class);

	@Autowired
	ISsoUserService ssoUserService;

	@RequestMapping("/list")
	public String list(SsoUser ssoUser, ModelMap model, Integer page) {


		try{
			PageInfo<SsoUser> pageInfo = ssoUserService.listByObject(ssoUser, page);
    		List<SsoUser> list = pageInfo.getData();
        	model.put("ssoUser", list);
		}catch (Exception e){
			logger.error("[获取列表] 失败", e);
		}
		return "/sso_user/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String toAdd(SsoUser ssoUser, ModelMap model) {
		return "/sso_user/add";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(SsoUser ssoUser, ModelMap model) {
		try {
			ssoUserService.save(ssoUser);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_user/add";
	}

	@RequestMapping(value = "/edit",method={RequestMethod.GET})
	public String toEdit(SsoUser ssoUser, ModelMap model){
		ssoUser = ssoUserService.selectById(ssoUser.getId());
		model.put("ssoUser", ssoUser);
		return "/sso_user/edit";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(SsoUser ssoUser, ModelMap model) {
		try {
			ssoUserService.update(ssoUser);
			model.put("res", 0);
		} catch (Exception e) {
			model.put("res", 1);
            logger.error("system Error", e);
		}
		return "/sso_user/edit";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String delete(SsoUser ssoUser){
		try{
			ssoUserService.deleteById(ssoUser.getId());
		} catch (Exception e){
			logger.error("[删除] 失败， id：{}", ssoUser.getId(), e);
		}
		return "/sso_user/list";
	}


}
