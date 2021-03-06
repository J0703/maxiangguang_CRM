package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.common.domain.PageBean;
import com.lanou.hr.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
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

    private int pageNum;
    private PageBean<Department> pageBean;

    // 每页显示记录数
    private int pageSize = 4;

    /**
     * 添加,修改部门
     *
     * @return
     */
    public String add() {

        // 添加判空
        if (StringUtils.isBlank(department.getDepName())){
            addActionError("部门名称不能为空");
            return ERROR;
        }

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
    public String findAllDepart(){

        pageBean = departmentService.findAll(pageNum, pageSize);

        return SUCCESS;
    }

    @Override
    public Department getModel() {
        department = new Department();
        return department;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public PageBean<Department> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Department> pageBean) {
        this.pageBean = pageBean;
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
