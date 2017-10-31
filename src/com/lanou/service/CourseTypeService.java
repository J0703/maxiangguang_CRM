package com.lanou.service;

import com.lanou.domain.CourseType;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
public interface CourseTypeService extends BaseService<CourseType> {

    void save(CourseType courseType);

    void update(CourseType courseType);


    List<CourseType> findAll();

    CourseType findById(String courseId);
}
