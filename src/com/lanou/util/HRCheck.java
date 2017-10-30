package com.lanou.util;

import com.lanou.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dllo on 17/10/30.
 */
public class HRCheck extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        Staff staff = (Staff) ActionContext.getContext().getSession().get("staff");

        if (!staff.getPost().getDepartment().getDepName().equals("人力资源部")){

            ActionContext.getContext().getSession().put("msg", "您没有该权限");
            return "error";
        }
        return actionInvocation.invoke();
    }
}
