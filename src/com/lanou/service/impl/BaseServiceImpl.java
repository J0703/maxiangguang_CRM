package com.lanou.service.impl;

import com.lanou.dao.BaseDao;
import com.lanou.domain.PageBean;
import com.lanou.domain.Post;
import com.lanou.service.BaseService;

import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    @Override
    public int getTotalCord(T tClass,String condition, Object[] params) {
        return baseDao.getTotalRecord(tClass, condition, params);
    }

    @Override
    public PageBean<T> findAll(T tClass, String condition, Object[] params, int pageNum, int pageSize) {

        String hql = "from "+ tClass + " where 1 = 1";

        // 条件为空不拼接
        if (null != condition){
            hql += condition;
        }

        // 查询出总的记录数(没有条件, 参数)
        int totalRecord = baseDao.getTotalRecord(tClass, hql, params);

        // 判断当前页是否为空
        if (pageNum == 0) pageNum = 1;

        // 定义总页数
        int totalPage = 0;

        // 总记录数为0,总页数为1, 否则通过计算公式得出
        totalPage = 0 == totalRecord ? 1 : totalRecord;

        // 计算总页数
        if (totalRecord != 0) {
            if (totalRecord % pageSize == 0) {
                totalPage = totalRecord / pageSize;
            } else {
                totalPage = totalRecord / pageSize + 1;
            }
        }

        // 创建pageBean对象
        PageBean<T> pageBean = new PageBean(pageNum, pageSize, totalRecord, totalPage);


        // 将查询到的数据封装到 pageBean中
        List<T> data = (List<T>) baseDao.findAll(tClass, condition, params, pageNum, pageSize);

        pageBean.setData(data);

        return pageBean;

    }



    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
}
