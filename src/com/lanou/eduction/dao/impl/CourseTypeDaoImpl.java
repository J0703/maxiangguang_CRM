package com.lanou.eduction.dao.impl;

import com.lanou.common.dao.impl.BaseDaoImpl;
import com.lanou.eduction.dao.CourseTypeDao;
import com.lanou.eduction.domain.CourseType;
import org.hibernate.SQLQuery;

/**
 * Created by dllo on 17/10/30.
 */
public class CourseTypeDaoImpl extends BaseDaoImpl<CourseType> implements CourseTypeDao {

    // 重写Update方法
    @Override
    public void update(CourseType courseType) {

        SQLQuery query = currentSession().createSQLQuery(
                "UPDATE CourseType SET courseName=?, courseCost=?, total=?, remark=? where courseId = ?");
        query.setString(0, courseType.getCourseName());
        query.setDouble(1, courseType.getCourseCost());
        query.setInteger(2, courseType.getTotal());
        query.setString(3, courseType.getRemark());
        query.setString(4, courseType.getCourseId());

        query.executeUpdate();

    }
}
