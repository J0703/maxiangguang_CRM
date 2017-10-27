package com.lanou.dao;


import com.lanou.domain.Department;

import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
public interface DepartmentDao extends BaseDao<Department> {
    /**
     * 获取总记录数
     * @param condition
     * @param params
     * @return
     */
    int getTotalRecord(String condition, Object[] params);


    List<Department> findAll(String condition, Object[] params, int pc, int ps);
}
