package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

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
        getHibernateTemplate().update(t);
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

}
