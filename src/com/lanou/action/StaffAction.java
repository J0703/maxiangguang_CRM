package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */

@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {

    // 用于接收数据
    private Staff staff;

    // 密码
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;

    private String postId;
    private String depId;
    private String staffId;



    @Resource
    private StaffService staffService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PostService postService;
    private List<Staff> staffs;

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

    /**
     *  退出登录
     * @return
     */
    public String logout(){
        ActionContext.getContext().getApplication().remove("staff");
        return SUCCESS;
    }

    /**
     *  查询所有员工
     * @return
     */
    public String findAll(){
        staffs = staffService.findAll("from Staff");

        for (Staff staff1 : staffs) {
            Object[] params = {staff1.getPost().getPostId()};
            Post post = postService.findSingle("from Post where postId = ?", params);

            Object[] params1 = {post.getDepartment().getDepId()};
            Department department = departmentService.findSingle("from Department where depId = ?", params1);

            staff1.setDepartment(department);
        }

        return SUCCESS;
    }

    /**
     *  添加员工
     * @return
     */
    public String add(){

        System.out.println(staff);

        Object[] params = {postId};
        Post post = postService.findSingle("from Post where postId =?", params);
        staff.setPost(post);

        staffService.save(staff);
        return SUCCESS;
    }

    /**
     *  添加验证
     * @return
     */
    public String validateAdd(){
        if (StringUtils.isBlank(staff.getLoginName())){
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(staff.getLoginPwd())){
            addActionError("密码不能为空");
        }
        if (StringUtils.isBlank(staff.getStaffName())){
            addActionError("用户名不能为空");
        }
        if (StringUtils.isBlank(staff.getOnDutyDate().toString())){
            addActionError("入职时间不能为空");
        }
        if (StringUtils.isBlank(depId)){
            addActionError("部门不能为空");
        }
        if (StringUtils.isBlank(postId)){
            addActionError("职务不能为空");
        }

        return SUCCESS;
    }

    /**
     *  编辑准备
     * @return
     */
    public String prepareEdit(){
        Object[] params = {staffId};
        Staff single = staffService.findSingle("from Staff where staffId =?", params);

        Object[] params2 = {single.getPost().getPostId()};
        Post post2 = postService.findSingle("from Post where postId =?", params2);
        single.setDepartment(post2.getDepartment());

        staff = single;
        return SUCCESS;
    }




    @Override
    public Staff getModel() {
        staff = new Staff();
        return staff;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
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

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }


    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
