package com.lanou.action;

import com.lanou.domain.CourseType;
import com.lanou.domain.PageBean;
import com.lanou.service.CourseTypeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("courseTypeAction")
@Scope("prototype")
public class CourseTypeAction extends ActionSupport implements ModelDriven<CourseType>{

    @Resource
    private CourseTypeService courseTypeService;

    private CourseType courseType;

    private PageBean<CourseType> pageBean;

    /**
     *   添加
     * @return
     */
    public String add(){

        courseTypeService.save(courseType);

        return SUCCESS;
    }

    /**
     *  查询所有
     * @return
     */
    public String findAll(){




        return SUCCESS;
    }

    @Override
    public CourseType getModel() {
        courseType = new CourseType();
        return courseType;
    }

    public CourseTypeService getCourseTypeService() {
        return courseTypeService;
    }

    public void setCourseTypeService(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }



    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public PageBean<CourseType> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<CourseType> pageBean) {
        this.pageBean = pageBean;
    }
}
