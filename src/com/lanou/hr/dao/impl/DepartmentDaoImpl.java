package com.lanou.hr.dao.impl;

import com.lanou.common.dao.impl.BaseDaoImpl;
import com.lanou.hr.dao.DepartmentDao;
import com.lanou.hr.domain.Department;
import com.lanou.common.util.PageHibernateCallback;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {


    @Override
    public int getTotalRecord(String condition, Object[] params) {
        String hql = "select count(d) from Department d where 1=1";
        hql += null == condition ? "" : condition;

        List<Long> find = (List<Long>) this.getHibernateTemplate().find(hql, params);

        if (find!=null){
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Department> findAll(String condition, Object[] params, int pageNum, int pageSize) {

        String hql =  "from Department where 1=1 ";

        // 条件判断
        hql += null == condition ? "" : condition;

        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<Department>(hql, params, (pageNum - 1) * pageSize, pageSize));
    }


}
