package co.bugu.sso.service.impl;

import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import co.bugu.sso.model.SsoPermission;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.bugu.sso.service.ISsoPermissionService;

@Service
public class SsoPermissionServiceImpl implements ISsoPermissionService {
	@Autowired
	BaseDao baseDao;


	@Override
	public int save(SsoPermission ssoPermission) {
		return baseDao.insert("sso.permission.insert", ssoPermission);
	}


	@Override
	public SsoPermission selectById(Integer id) {
		return baseDao.selectOne("sso.permission.selectById", id);
	}

	@Override
	public int update(SsoPermission ssoPermission) {
		return baseDao.update("sso.permission.updateById", ssoPermission);
	}

	@Override
	public int deleteById(Integer id) {
		return baseDao.delete("sso.permission.deleteById", id);
	}


	@Override
	public PageInfo<SsoPermission> listByObject(SsoPermission ssoPermission, Integer page) throws Exception {
		return baseDao.listByObject("sso.permission.listByObject", ssoPermission, new PageInfo(page));
	}
}
