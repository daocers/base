package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Department;
import co.bugu.tes.service.IDepartmentService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
    @Override
    public void batchAdd(List<Department> departmentList) {

    }
//    @Autowired
//    BaseDao baseDao;
//
//    @Override
//    public int save(Department department) {
//        return baseDao.insert("tes.department.insert", department);
//    }
//
//    @Override
//    public int updateById(Department department) {
//        return baseDao.update("tes.department.updateById", department);
//    }
//
//    @Override
//    public int saveOrUpdate(Department department) {
//        if(department.getId() == null){
//            return baseDao.insert("tes.department.insert", department);
//        }else{
//            return baseDao.update("tes.department.updateById", department);
//        }
//    }
//
//    @Override
//    public int delete(Department department) {
//        return baseDao.delete("tes.department.deleteById", department);
//    }
//
//    @Override
//    public Department findById(Integer id) {
//        return baseDao.selectOne("tes.department.selectById", id);
//    }
//
//    @Override
//    public List<Department> findAllByObject(Department department) {
//        return baseDao.selectList("tes.department.listByObject", department);
//    }
//
//    @Override
//    public PageInfo listByObject(Department department, PageInfo<Department> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.department.listByObject", department, pageInfo);
//    }
}
