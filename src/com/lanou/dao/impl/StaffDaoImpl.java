package com.lanou.dao.impl;

import com.lanou.dao.StaffDao;
import com.lanou.domain.Staff;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

    @Override
    public void update(Staff staff) {
        Session session = currentSession();
        SQLQuery sqlQuery=session.createSQLQuery(
                "UPDATE Staff SET loginName=?, loginPwd=?, staffName=?, gender=?, onDutyDate=?, postId=? WHERE staffId =?");

        sqlQuery.setParameter(1, staff.getLoginName());
        sqlQuery.setParameter(2, staff.getLoginPwd());
        sqlQuery.setParameter(3, staff.getStaffName());
        sqlQuery.setParameter(4, staff.getGender());
        sqlQuery.setParameter(5, staff.getOnDutyDate());
        sqlQuery.setParameter(6, staff.getPost().getPostId());
        sqlQuery.setParameter(7, staff.getStaffId());

        sqlQuery.executeUpdate();

    }
}
