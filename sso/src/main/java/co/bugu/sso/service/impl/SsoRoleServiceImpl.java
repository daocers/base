package co.bugu.sso.service.impl;

import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.bugu.sso.model.SsoRole;
import co.bugu.sso.service.ISsoRoleService;

@Service
public class SsoRoleServiceImpl implements ISsoRoleService {
	@Autowired
	BaseDao baseDao;


	@Override
	public int save(SsoRole ssoRole) {
		return baseDao.insert("sso.role.insert", ssoRole);
	}


	@Override
	public SsoRole selectById(Integer id) {
		return baseDao.selectOne("sso.role.selectById", id);
	}

	@Override
	public int update(SsoRole ssoRole) {
		return baseDao.update("sso.role.updateById", ssoRole);
	}

	@Override
	public int deleteById(Integer id) {
		return baseDao.delete("sso.role.deleteById", id);
	}


	@Override
	public PageInfo<SsoRole> listByObject(SsoRole ssoRole, Integer page) throws Exception {
		return baseDao.listByObject("sso.role.listByObject", ssoRole, new PageInfo(page));
	}
}
