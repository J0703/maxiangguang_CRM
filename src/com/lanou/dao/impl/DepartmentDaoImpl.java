package com.lanou.dao.impl;

import com.lanou.dao.DepartmentDao;
import com.lanou.domain.Department;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {


    @Override
    public int getTotalRecord(String condition, Object[] params) {
        String hql = "select count(*) from Department where 1=1" + condition;

        List<Long> find = (List<Long>) this.getHibernateTemplate().find(hql, params);

        if (find!=null){
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Department> findAll(String condition, Object[] params, int pc, int ps) {
        return null;
    }


}
