package com.lanou.hr.service.impl;

import com.lanou.hr.dao.DepartmentDao;
import com.lanou.hr.domain.Department;
import com.lanou.common.domain.PageBean;
import com.lanou.hr.service.DepartmentService;

import java.io.Serializable;
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
    public PageBean<Department> findAll(int pageNum, int pageSize) {


        // 查询出总的记录数(没有条件, 参数)
        int totalRecord = departmentDao.getTotalRecord(null, null);

        // 判断是否是空
        if (pageNum == 0) pageNum = 1;

        // 定义总页数
        int totalPage = 0;

        // 总记录数为0,总页数为1, 否则通过计算公式得出
        totalPage = 0 == totalRecord ? 1 : totalRecord;
        if (totalRecord != 0) {
            if (totalRecord % pageSize == 0) {
                totalPage = totalRecord / pageSize;
            } else {
                totalPage = totalRecord / pageSize + 1;
            }
        }

        // 创建pageBean对象
        PageBean<Department> pageBean = new PageBean(pageNum, pageSize, totalRecord, totalPage);


        // 将查询到的数据封装到 pageBean中
        List<Department> data = departmentDao.findAll(null, null, pageNum, pageSize);

        pageBean.setData(data);

        return pageBean;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
