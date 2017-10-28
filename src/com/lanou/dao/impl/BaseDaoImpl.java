package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import com.lanou.domain.Department;
import com.lanou.util.PageHibernateCallback;
import org.hibernate.Query;
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
    public int getTotalRecord(T tClass, String condition, Object[] params) {


        String hql = "select count(d) from " + tClass + " d where 1=1";
        List<Long> totalRecord;
        if (params == null){
            totalRecord = (List<Long>) this.getHibernateTemplate().find(hql);
        }else {
            totalRecord = (List<Long>) this.getHibernateTemplate().find(hql, params);
        }

        return totalRecord != null ? totalRecord.get(0).intValue() : 0;
    }

    @Override
    public List<Department> findAll(T t, String condition, Object[] params, int pageNum, int pageSize) {

        String hql =  "from "+ t +" where 1=1 ";

        // 条件判断
        hql += null == condition ? "" : condition;

        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<Department>(hql, params, pageNum, pageSize));
    }

}
