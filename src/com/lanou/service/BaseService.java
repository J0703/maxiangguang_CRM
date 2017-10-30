package com.lanou.service;

import com.lanou.domain.PageBean;

/**
 * Created by dllo on 17/10/28.
 */
public interface BaseService<T> {


    PageBean<T> findAll(T tClass, String condition, Object[] params, int pageNum, int pageSize);

    

}
