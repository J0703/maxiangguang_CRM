package com.lanou.action;

import com.lanou.domain.Staff;
import com.lanou.service.StaffService;
import com.lanou.service.impl.StaffServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */

@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {

    // 用于接收数据
    private Staff staff;

    private String oldPassword;
    private String newPassword;
    private String reNewPassword;

    @Resource
    private StaffService staffService;

    /**
     * 用户登录
     *
     * @return
     */
    public String login() {
        // 用户表单回填
        ActionContext.getContext().getApplication().put("staff", staff);

        // 数据库查询
        Staff staff1 = staffService.login(staff);
        if (null != staff1) {
            ActionContext.getContext().getApplication().put("staff", staff1);
            return SUCCESS;
        }
        // 如果没有该用户, 添加错误信息
        addActionError("该用户不存在 或 密码错误");
        return ERROR;
    }

    /**
     * 登录验证
     *
     * @return
     */
    public String validateLogin() {
        if (StringUtils.isBlank(staff.getLoginName())) {
            addActionError("用户名不能为空");
        }
        if (StringUtils.isBlank(staff.getLoginPwd())) {
            addActionError("密码不能为空");
        }
        return SUCCESS;
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String editLoginPwd() {
        // 获得staff
        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staff");

        // 验证旧密码
        if (staff.getLoginPwd().equals(oldPassword)) {
            // 调用修改方法
            staff.setLoginPwd(newPassword);
            staffService.update(staff);
            return SUCCESS;
        }
        return ERROR;
    }

    /**
     *  验证输入
     * @return
     */
    public String validateEditLoginPwd() {

        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(reNewPassword);

        if (StringUtils.isBlank(oldPassword)) {
            addActionError("旧密码不能为空");
        }
        if (StringUtils.isBlank(reNewPassword)) {
            addActionError("确定新密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            addActionError("新密码不能为空");
        }
        if (!StringUtils.isBlank(reNewPassword) && !StringUtils.isBlank(oldPassword)){
            if (!reNewPassword.equals(newPassword)) {
                addActionError("两次密码不一致");
            }
        }
        return SUCCESS;
    }

    public String logout(){
        ActionContext.getContext().getApplication().remove("staff");
        return SUCCESS;
    }


    @Override
    public Staff getModel() {
        staff = new Staff();
        return staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
