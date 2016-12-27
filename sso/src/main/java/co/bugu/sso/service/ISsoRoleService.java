package co.bugu.sso.service;

import co.bugu.sso.model.SsoRole;
import co.bugu.framework.core.dao.PageInfo;

public interface ISsoRoleService {

	int save(SsoRole ssoRole);

	int update(SsoRole ssoRole);

	
	SsoRole selectById(Integer id);

	int deleteById(Integer id);


	PageInfo<SsoRole> listByObject(SsoRole ssoRole, Integer page) throws Exception;
	
}
