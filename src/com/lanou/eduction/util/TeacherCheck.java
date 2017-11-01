package com.lanou.eduction.util;

import com.lanou.hr.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dllo on 17/10/31.
 */
public class TeacherCheck extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staff");

        if (!staff.getPost().getDepartment().getDepId().equals("2c9090cb5f585a97015f585b1cdd0001")){

            ActionContext.getContext().getSession().put("msg", "您不是教研部的银");

            return "error";
        }
        return actionInvocation.invoke();
    }
}
