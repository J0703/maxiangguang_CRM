package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.common.domain.PageBean;
import com.lanou.hr.domain.Post;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.lanou.hr.service.StaffService;
import com.lanou.hr.util.EncryptUtil;
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

    // 高级查询 二级联动参数
    private String postId;
    private String depId;
    private String staffId;
    private String staffName;

    private List<Department> departments;
    private List<Post> posts;

    // 分页
    private int pageSize = 4;
    private int pageNum;//当前页
    private PageBean<Staff> pageBean;


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

        // 密码加密判断
        String pwd = EncryptUtil.getMD5Value(staff.getLoginPwd());
        staff.setLoginPwd(pwd);

        // 数据库查询
        Staff staff1 = staffService.login(staff);
        if (null != staff1) {
            ActionContext.getContext().getApplication().put("staff", staff1);
            ActionContext.getContext().getSession().put("staff", staff1);
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
        String md5Value = EncryptUtil.getMD5Value(oldPassword);

        if (!staff.getLoginPwd().equals(md5Value)){
            addActionError("旧密码不正确");
            return ERROR;
        }

        if (staff.getLoginPwd().equals(md5Value)) {
            // 调用修改方法
            staff.setLoginPwd(EncryptUtil.getMD5Value(newPassword));
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
        ActionContext.getContext().getSession().clear();
        ActionContext.getContext().getApplication().clear();
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
     * 编辑员工
     *
     * @return
     */
    public String edit() {

        // 给员工设置职务
        Department byId = departmentService.findById(postId);
        Post post = postService.get(postId);

        staff.setDepartment(byId);
        staff.setPost(post);

        Staff byStaffId = staffService.findByStaffId(staff.getStaffId());

        System.out.println(byStaffId);
        System.out.println(staff);

        // 执行修改
        staffService.update(staff);


        return SUCCESS;
    }


    /**
     * 高级查询
     *
     * @return
     */
    public String find() {

        System.out.println(depId);
        System.out.println(postId);
        System.out.println(staff.getStaffName());

        staffs = staffService.find(depId, postId, staff.getStaffName());

        return SUCCESS;
    }

    /**
     * 分页,  查询所有 和 条件查询
     *
     * @return
     */
    public String findAllStaff() {

        // 查询所有
        if (null == depId && null == postId && null == staff.getStaffName()) {

            pageBean = staffService.findAll(staff, null, null, pageNum, pageSize);

        } else {

            // 条件查询

            String condition = "";
            List params = new ArrayList();

            // depId为空,进行参数拼接
            if (!"".equals(depId)) {
                condition += "and s.post.department.depId = ? ";
                params.add(depId);
            }
            // postId为空,进行参数拼接
            if (!"".equals(postId)) {
                condition += "and s.post.postId =? ";
                params.add(postId);
            }
            // staffName为空,进行参数拼接
            if (!"".equals(staff.getStaffName())) {
                condition += "and s.staffName like '%" + staff.getStaffName() + "%'";
            }
            pageBean = staffService.findAll(staff, condition, params.toArray(), pageNum, pageSize);

        }
        return SUCCESS;
    }


    @Override
    public Staff getModel() {
        staff = new Staff();
        return staff;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public PageBean<Staff> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Staff> pageBean) {
        this.pageBean = pageBean;
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
