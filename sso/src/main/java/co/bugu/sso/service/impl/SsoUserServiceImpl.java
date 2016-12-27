package co.bugu.sso.service.impl;

import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.bugu.sso.model.SsoUser;
import co.bugu.sso.service.ISsoUserService;

@Service
public class SsoUserServiceImpl implements ISsoUserService {
	@Autowired
	BaseDao baseDao;


	@Override
	public int save(SsoUser ssoUser) {
		return baseDao.insert("sso.user.insert", ssoUser);
	}


	@Override
	public SsoUser selectById(Integer id) {
		return baseDao.selectOne("sso.user.selectById", id);
	}

	@Override
	public int update(SsoUser ssoUser) {
		return baseDao.update("sso.user.updateById", ssoUser);
	}

	@Override
	public int deleteById(Integer id) {
		return baseDao.delete("sso.user.deleteById", id);
	}


	@Override
	public PageInfo<SsoUser> listByObject(SsoUser ssoUser, Integer page) throws Exception {
		return baseDao.listByObject("sso.user.listByObject", ssoUser, new PageInfo(page));
	}

	@Override
	public SsoUser selectByObject(SsoUser ssoUser) throws Exception {
		PageInfo<SsoUser> pageInfo = baseDao.listByObject("sso.user.listByObject", ssoUser, new PageInfo<SsoUser>(1));
		if(pageInfo.getData().size() > 1){
			throw  new Exception("查询记录多于一条，数据紊乱");
		}
		SsoUser user = pageInfo.getData().get(0);
		return user;
	}
}
