package com.lanou.service.impl;

import com.lanou.dao.BaseDao;
import com.lanou.domain.*;
import com.lanou.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;


    @Override
    public PageBean<T> findAll(T tClass, String condition, Object[] params, int pageNum, int pageSize) {

        // 根据类型判断使用的查询语句
        String hql = "";
        if(tClass instanceof Post){
            hql = "from Post where 1 = 1 ";
        }
        if (tClass instanceof Department){
            hql += "from Department where 1= 1 ";
        }
        if (tClass instanceof Staff){
            hql += "from Staff s where 1 = 1 ";
        }
        if (tClass instanceof CourseType){
            hql += "from CourseType where 1 = 1 ";
        }
        if (tClass instanceof Classes){
            hql += "from Classes where 1 = 1 ";
        }


        // 条件为空不拼接
        if (null != condition){
            hql += condition;
        }

        // 查询出总的记录数(没有条件, 参数)
        int totalRecord = baseDao.getTotalRecord(hql, params);

        // 判断当前页是否为0
        if (pageNum == 0) pageNum = 1;

        // 定义总页数
        int totalPage = getTotalPage(pageSize, totalRecord);

        // 创建pageBean对象
        PageBean<T> pageBean = new PageBean(pageNum, pageSize, totalRecord, totalPage);

        // 将查询到的数据封装到 pageBean中
        List<T> data = baseDao.find(hql, params, pageNum, pageSize);

        pageBean.setData(data);

        return pageBean;

    }

//    @Override
//    public void update(T classes) {
//        String hql = "";
//        List params = new ArrayList();
//
//        if (classes instanceof Classes){
//            hql = "update Classes set classesName=?, " +
//                    "beginTime=?, endTime=?,status=?," +
//                    "totalCount=?,upgradeCount=?,changeCount=?" +
//                    "runoffCount=?,remark=?,uploadTime=?," +
//                    "uploadPath=?,uploadFilename=? where classesId="+((Classes) classes).getClassId();
//
//            params.add(0, ((Classes) classes).getClassName());
//            params.add(1, ((Classes) classes).getBeginTime());
//            params.add(2, ((Classes) classes).getEndTime());
//            params.add(3, ((Classes) classes).getStatus());
//            params.add(4, ((Classes) classes).getTotalCount());
//            params.add(5, ((Classes) classes).getUpgradeCount());
//            params.add(6, ((Classes) classes).getChangeCount());
//            params.add(7, ((Classes) classes).getRunoffCount());
//            params.add(8, ((Classes) classes).getRemark());
//            params.add(9, ((Classes) classes).getUploadTime());
//            params.add(10, ((Classes) classes).getUploadPath());
//            params.add(11, ((Classes) classes).getUploadFilename());
//
//        }
//        Object[] objects = params.toArray();
//
//        baseDao.update(hql, objects);
//
//    }

    private int getTotalPage(int pageSize, int totalRecord) {

        int totalPage = 0;

//        // 总记录数为0,总页数为1, 否则通过计算公式得出
//        totalPage = 0 == totalRecord ? 1 : totalRecord;

        // 计算总页数
        if (totalRecord != 0) {
            if (totalRecord % pageSize == 0) {
                totalPage = totalRecord / pageSize;
            } else {
                totalPage = totalRecord / pageSize + 1;
            }
        }
        return totalPage;
    }

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
}
