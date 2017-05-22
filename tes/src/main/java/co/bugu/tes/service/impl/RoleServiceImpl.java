package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Role;
import co.bugu.tes.service.IRoleService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
//    @Autowired
//    BaseDao baseDao;
//
//    @Override
//    public int save(Role role) {
//        return baseDao.insert("tes.role.insert", role);
//    }
//
//    @Override
//    public int updateById(Role role) {
//        return baseDao.update("tes.role.updateById", role);
//    }

    @Override
    public void save(Role role, List<Map<String, Integer>> xList) {
        if(role.getId() == null){
            baseDao.insert("tes.role.insert", role);
        }else{
            baseDao.update("tes.role.updateById", role);
        }
        Map<String, Integer> param = new HashMap<>();
        param.put("roleId", role.getId());
        baseDao.delete("tes.role.deleteRoleAuthX", param);

        for(Map<String, Integer> map: xList){
            map.put("roleId", role.getId());
            baseDao.insert("tes.role.addRoleAuthX", map);
        }
    }

    @Override
    public List<Role> selectRoleByUser(Integer userId) {
        return baseDao.selectList("tes.role.selectRoleByUser", userId);
    }

    //    @Override
//    public int delete(Role role) {
//        return baseDao.delete("tes.role.deleteById", role);
//    }
//
    @Override
    public Role findById(Integer id) {
        Role role =  baseDao.selectOne("tes.role.selectById", id);
        if(role != null){
            List<Authority> authorities = baseDao.selectList("tes.authority.selectAuthorityByRole", role.getId());
            role.setAuthorityList(authorities);
        }
        return role;
    }
//
//    @Override
//    public List<Role> findAllByObject(Role role) {
//        return baseDao.selectList("tes.role.listByObject", role);
//    }
//
//    @Override
//    public PageInfo listByObject(Role role, PageInfo<Role> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.role.listByObject", role, pageInfo);
//    }
}
