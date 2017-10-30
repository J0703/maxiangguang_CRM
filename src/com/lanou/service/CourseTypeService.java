package com.lanou.service;

import com.lanou.domain.CourseType;

/**
 * Created by dllo on 17/10/30.
 */
public interface CourseTypeService extends BaseService<CourseType> {

    void save(CourseType courseType);
}
