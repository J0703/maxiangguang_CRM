package com.lanou.eduction.service.impl;

import com.lanou.eduction.dao.CourseTypeDao;
import com.lanou.eduction.domain.CourseType;
import com.lanou.eduction.service.CourseTypeService;
import com.lanou.common.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
public class CourseTypeServiceImpl extends BaseServiceImpl<CourseType> implements CourseTypeService {

    private CourseTypeDao courseTypeDao;

    @Override
    public void save(CourseType courseType) {
        courseTypeDao.save(courseType);
    }

    @Override
    public void update(CourseType courseType) {
        courseTypeDao.update(courseType);
    }

    @Override
    public List<CourseType> findAll() {
        return courseTypeDao.find("from CourseType", null);
    }

    @Override
    public CourseType findById(String courseId) {
        String hql = "from CourseType where courseId =?";
        Object[] param = {courseId};
        return courseTypeDao.findSingle(hql, param);
    }


    public CourseTypeDao getCourseTypeDao() {
        return courseTypeDao;
    }

    public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
        this.courseTypeDao = courseTypeDao;
    }



}
