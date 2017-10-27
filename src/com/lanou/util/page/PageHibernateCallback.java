package com.lanou.util.page;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

    private String hql;//查询语句
    private Object[] params;//参数
    private int startIndex;//开始索引
    private int pageSize;//每页记录数

    public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
        super();
        this.hql = hql;
        this.params = params;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    @Override
    public List<T> doInHibernate(Session session) throws HibernateException {
        // 通过hql获得Query对象
        Query query = session.createQuery(hql);

        // 设置条件
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        // 分页
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return query.list();
    }
}
