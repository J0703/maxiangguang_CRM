package com.lanou.common.dao.impl;

import com.lanou.common.dao.BaseDao;
import com.lanou.hr.domain.Department;
import com.lanou.common.util.PageHibernateCallback;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/17.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        Session session = currentSession();
        session.merge(t);
    }

    @Override
    public List<T> findAll(String hql) {
        return (List<T>) getHibernateTemplate().find(hql);
    }

    @Override
    public List<T> find(String hql, Object[] params) {
        return (List<T>) getHibernateTemplate().find(hql, params);
    }

    @Override
    public T findSingle(String hql, Object[] params) {
        List<T> tList = (List<T>) getHibernateTemplate().find(hql, params);
        if (tList.size() > 0) {
            return tList.get(0);
        }
        return null;
    }

    @Override
    public T get(Class<T> tClass, Serializable id) {
        Session session = currentSession();
        T t = (T) session.get(tClass, id);
        return t;
    }

    @Override
    public int getTotalRecord(String hql, Object[] params) {
        // 定义总页数
        int totalRecord;
        if (params == null){
            totalRecord = this.getHibernateTemplate().find(hql).size();
        }else {
            totalRecord = this.getHibernateTemplate().find(hql, params).size();
        }

        return totalRecord != 0 ? totalRecord : 0;
    }

    @Override
    public List<Department> findAll(T t, String condition, Object[] params, int pageNum, int pageSize) {

        String hql =  "from "+ t +" where 1=1 ";

        // 条件判断
        hql += null == condition ? "" : condition;

        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<Department>(hql, params, pageNum, pageSize));
    }

    @Override
    public List<T> find(String hql, Object[] params, int pageNum, int pageSize) {
        return getHibernateTemplate().execute(new PageHibernateCallback<T>(hql, params, (pageNum-1)*pageSize, pageSize));
    }

//    @Override
//    public void update(String hql, Object[] params) {
//        SQLQuery sqlQuery = currentSession().createSQLQuery(hql);
//
//        for (int i = 0; i < params.length; i++) {
//            sqlQuery.setParameter(i, params[i]);
//        }
//
//        sqlQuery.executeUpdate();
//    }

}
