package com.lanou.service.impl;

import com.lanou.dao.DepartmentDao;
import com.lanou.domain.Department;
import com.lanou.util.page.PageBean;
import com.lanou.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 17/10/25.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;


    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll("from Department");
    }

    @Override
    public Department findByName(String depName) {
        Object[] params = {depName};
        return departmentDao.findSingle("from Department where depId=?", params);
    }

    @Override
    public Department findById(String depId) {
        Object[] params = {depId};
        return departmentDao.findSingle("from Department where depId=?", params);
    }

    @Override
    public Department get(Serializable id) {
        return departmentDao.get(Department.class, id);
    }

    @Override
    public PageBean<Department> findAll(Department department, int pageNum, int pageSize) {
        // 拼接条件查询
        StringBuilder sbCondition = new StringBuilder();

        List<Object> paramsList = new ArrayList<>();

        // 过滤条件
        if (StringUtils.isBlank(department.getDepName())){

        }

        return null;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
