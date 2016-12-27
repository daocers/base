package co.bugu.sso.service;

import co.bugu.sso.model.SsoUser;
import co.bugu.framework.core.dao.PageInfo;

public interface ISsoUserService {

	int save(SsoUser ssoUser);

	int update(SsoUser ssoUser);

	
	SsoUser selectById(Integer id);

	int deleteById(Integer id);


	PageInfo<SsoUser> listByObject(SsoUser ssoUser, Integer page) throws Exception;

	SsoUser selectByObject(SsoUser ssoUser) throws Exception;
}
