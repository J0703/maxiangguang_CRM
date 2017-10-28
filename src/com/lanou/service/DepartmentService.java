package com.lanou.service;

import com.lanou.domain.Department;
import com.lanou.domain.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface DepartmentService {
    /**
     *  保存某个对象
     * @return  保存之后的对象
     */
    void save(Department department);

    /**
     *  删除某个对象
     *
     */
    void delete(Department department);

    /**
     *  更改某个对象
     *
     */
    void update(Department department);

    /**
     * 查询所有
     * @return
     */
    List<Department> findAll();

    /**
     *  根据条件查询, 返回查询到的结果集合
     * @return 查询到的结果集合
     */
    Department findByName (String depName);

    /**
     *  查询单个
     * @return
     */
    Department findById(String depName);

    Department get(Serializable id);

    PageBean<Department> findAll(int pageNum, int pageSize);
}
