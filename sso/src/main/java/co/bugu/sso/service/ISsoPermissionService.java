package co.bugu.sso.service;

import co.bugu.sso.model.SsoPermission;
import co.bugu.framework.core.dao.PageInfo;

public interface ISsoPermissionService {

	int save(SsoPermission ssoPermission);

	int update(SsoPermission ssoPermission);

	
	SsoPermission selectById(Integer id);

	int deleteById(Integer id);


	PageInfo<SsoPermission> listByObject(SsoPermission ssoPermission, Integer page) throws Exception;
	
}
