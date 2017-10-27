package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.util.page.PageBean;
import com.lanou.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

    // 接收表单提交数据
    private Department department;
    // 存查询出的所有的部门, 用于页面的回显
    private List<Department> departments;

    // 获得业务对象
    @Resource
    private DepartmentService departmentService;
    private String pc;

    /**
     * 添加,修改部门
     *
     * @return
     */
    public String add() {

        // 查询数据库中是否存在
        Department findDept = departmentService.findById(department.getDepId());

        // 存在就跟新
        if (null != findDept) {
            departmentService.update(department);
            return SUCCESS;
        }
        // 不存在添加
        departmentService.save(department);
        return SUCCESS;

    }

    /**
     * 查询所有部门
     *
     * @return
     */
    public String findAll() {
        departments = departmentService.findAll();
        return SUCCESS;
    }

    /**
     *  分页
     * @return
     */
    public String query(){
        // 获取 当前页码数
        int pc = getPc(this.pc);

        // 指定 每页记录数
        int ps = 5;
        // 传递 pc , ps 获得pageBean
        departmentService.findAll();


        return SUCCESS;
    }

    private int getPc(String pc) {
        if (null == this.pc || this.pc.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(pc);
    }


    @Override
    public Department getModel() {
        department = new Department();
        return department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
