package com.lanou.service;

import com.lanou.domain.Staff;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService extends BaseService<Staff>{

    /**
     *  保存某个对象
     * @return  保存之后的对象
     */
    void save(Staff staff);

    /**
     *  删除某个对象
     *
     */
    void delete(Staff staff);

    /**
     *  更改某个对象
     *
     */
    void update(Staff staff);

    /**
     * 查询所有
     * @return
     */
    List<Staff> findAll();

    /**
     *  根据条件查询, 返回查询到的结果集合
     * @return 查询到的结果集合
     */
    Staff findByStaffId(String staffId);

    List<Staff> findByPostId(String postId);

    List<Staff> findByDepId(String depId);

    List<Staff> findByStaffName(String staffName);

    List<Staff> find(String depId, String postId, String staffName);

    /**
     *  查询单个
     * @param hql
     * @param params
     * @return
     */
    Staff findSingle(String hql, Object[] params);

    Staff get(Serializable id);
    
    Staff login(Staff staff);
}
