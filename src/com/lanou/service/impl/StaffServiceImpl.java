package com.lanou.service.impl;

import com.lanou.domain.Staff;
import com.lanou.service.StaffService;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {

    @Override
    public Staff login(Staff staff) {
        String hql = "from Staff where loginName=? and loginPwd=?";
        Object[] params ={staff.getLoginName(), staff.getLoginPwd()};
        Staff s = (Staff) getBaseDao().findSingle(hql, params);
        System.out.println(s);
        return s;
    }

}
