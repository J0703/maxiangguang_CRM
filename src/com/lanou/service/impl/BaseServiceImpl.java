package com.lanou.service.impl;

import com.lanou.dao.BaseDao;
import com.lanou.dao.impl.BaseDaoImpl;
import com.lanou.service.BaseService;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    
    private BaseDao<T> baseDao;

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public List<T> findAll(String hql) {
        return baseDao.findAll(hql);
    }

    @Override
    public List<T> find(String hql, Object[] params) {
        return baseDao.find(hql, params);
    }

    @Override
    public T findSingle(String hql, Object[] params) {
        return baseDao.findSingle(hql, params);
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }


}
