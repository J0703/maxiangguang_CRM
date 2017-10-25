package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
@Controller("department")
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

    // 接收表单提交数据
    private Department department;

    // 获得业务对象
    @Resource
    private DepartmentService departmentService;

    /**
     *  添加部门
     * @return
     */
    public String add(){
        System.out.println(department);
        departmentService.save(department);
        return SUCCESS;
    }

    /**
     *  查询所有部门
     * @return
     */
    public String findAll(){
        System.out.println(department);
        String hql = "from Department";
        List<Department> departments = departmentService.findAll(hql);
        ActionContext.getContext().getApplication().put("departments", departments);
        return SUCCESS;
    }

    @Override
    public Department getModel() {
        department = new Department();
        return null;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
