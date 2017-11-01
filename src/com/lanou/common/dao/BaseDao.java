package com.lanou.common.dao;

import com.lanou.hr.domain.Department;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/17.
 */
public interface BaseDao<T> {
    /**
     *  保存某个对象
     * @param t 要保存的对象
     * @return  保存之后的对象
     */
    void save(T t);

    /**
     *  删除某个对象
     *
     * @param t 要删除的对象, 必须是持久化状态的对象
     */
    void delete(T t);

    /**
     *  更改某个对象
     *
     * @param t 要更改的对象
     */
    void update(T t);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll(String hql);

    /**
     *  根据条件查询, 返回查询到的结果集合
     * @param hql    查询语句
     * @param params 参数列表
     * @return 查询到的结果集合
     */
    List<T> find(String hql, Object[] params);


    /**
     *  查询单个
     * @param hql
     * @param params
     * @return
     */
    T findSingle(String hql, Object[] params);

    T get(Class<T> tClass, Serializable id);

    int getTotalRecord(String hql, Object[] params);

    List<Department> findAll(T tClass,  String condition, Object[] params, int pageNum, int pageSize);


    List<T> find(String hql, Object[] params, int pageNum, int pageSize);


//    void update(String hql, Object[] objects);
}
