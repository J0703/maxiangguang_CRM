package com.lanou.service.impl;

import com.lanou.dao.CourseTypeDao;
import com.lanou.domain.CourseType;
import com.lanou.service.CourseTypeService;

/**
 * Created by dllo on 17/10/30.
 */
public class CourseTypeServiceImpl extends BaseServiceImpl<CourseType> implements CourseTypeService {

    private CourseTypeDao courseTypeDao;

    @Override
    public void save(CourseType courseType) {
        courseTypeDao.save(courseType);
    }


    public CourseTypeDao getCourseTypeDao() {
        return courseTypeDao;
    }

    public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
        this.courseTypeDao = courseTypeDao;
    }



}
