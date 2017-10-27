package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
import com.lanou.service.StaffService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private String staffName;

    private List<Department> departments;
    private List<Post> posts;


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
     * 验证输入
     *
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
        if (!StringUtils.isBlank(reNewPassword) && !StringUtils.isBlank(oldPassword)) {
            if (!reNewPassword.equals(newPassword)) {
                addActionError("两次密码不一致");
            }
        }
        return SUCCESS;
    }

    /**
     * 退出登录
     *
     * @return
     */
    public String logout() {
        ActionContext.getContext().getApplication().remove("staff");
        return SUCCESS;
    }

    /**
     * 查询所有员工
     *
     * @return
     */
    public String findAll() {
        staffs = staffService.findAll();
        return SUCCESS;
    }

    /**
     * 添加员工
     *
     * @return
     */
    public String add() {

        Post post = postService.findById(postId);
        staff.setPost(post);

        staffService.save(staff);
        return SUCCESS;
    }

    /**
     * 添加验证
     *
     * @return
     */
    public String validateAdd() {
        if (StringUtils.isBlank(staff.getLoginName())) {
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(staff.getLoginPwd())) {
            addActionError("密码不能为空");
        }
        if (StringUtils.isBlank(staff.getStaffName())) {
            addActionError("用户名不能为空");
        }
        if (StringUtils.isBlank(staff.getOnDutyDate().toString())) {
            addActionError("入职时间不能为空");
        }
        if (StringUtils.isBlank(depId)) {
            addActionError("部门不能为空");
        }
        if (StringUtils.isBlank(postId)) {
            addActionError("职务不能为空");
        }

        return SUCCESS;
    }

    /**
     * 编辑准备
     *
     * @return
     */
    public String prepareEdit() {

        Staff staff1 = staffService.get(staff.getStaffId());

        posts = postService.findByDepId(staff1.getPost().getDepartment().getDepId());

        departments = departmentService.findAll();

        return SUCCESS;
    }

    /**
     *  编辑员工
     * @return
     */
    public String edit(){
        System.out.println(staff);
        System.out.println(postId);

        // 给员工设置职务
        Post post = postService.get(postId);

        staff.setStaffId(staff.getStaffId());
        staff.setPost(post);

        System.out.println(staff);

        staffService.update(staff);


        return SUCCESS;
    }


    /**
     * 高级查询
     *
     * @return
     */
    public String find() {

        staffs = staffService.find(depId, postId, staffName);

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

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
