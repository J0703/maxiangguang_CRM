package com.lanou.hr.dao.impl;

import com.lanou.common.dao.impl.BaseDaoImpl;
import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

    @Override
    public void update(Staff staff) {

        Session session = currentSession();
        SQLQuery sqlQuery = session.createSQLQuery(
                "UPDATE crm_staff SET loginName=?, loginPwd=?, staffName=?, gender=?, onDutyDate=?, postId=? WHERE staffId =?");

        sqlQuery.setString(0, staff.getLoginName());
        sqlQuery.setString(1, staff.getLoginPwd());
        sqlQuery.setString(2, staff.getStaffName());
        sqlQuery.setString(3, staff.getGender());
        sqlQuery.setDate(4, staff.getOnDutyDate());
        sqlQuery.setString(5, staff.getPost().getPostId());
        sqlQuery.setString(6, staff.getStaffId());

        sqlQuery.executeUpdate();

    }

}
