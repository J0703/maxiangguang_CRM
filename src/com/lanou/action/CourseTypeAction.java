package com.lanou.action;

import com.lanou.domain.CourseType;
import com.lanou.domain.PageBean;
import com.lanou.service.CourseTypeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("courseTypeAction")
@Scope("prototype")
public class CourseTypeAction extends ActionSupport implements ModelDriven<CourseType> {

    @Resource
    private CourseTypeService courseTypeService;

    private CourseType courseType;

    private int pageNum;
    private int pageSize = 4;
    private PageBean<CourseType> pageBean;


    private double costStart;
    private double costEnd;
    private int totalStart;
    private int totalEnd;

    /**
     * 添加
     *
     * @return
     */
    public String add() {

        // 验证
        if (0 == courseType.getTotal()) {
            addActionError("学时不能为空");
        }
        if (StringUtils.isBlank(courseType.getCourseName())) {
            addActionError("课程名称不能为空");
        }
        if (0 == courseType.getCourseCost()) {
            addActionError("课程费用不能为空");
        }
        if (0 == courseType.getTotal() || StringUtils.isBlank(courseType.getCourseName()) || 0 == courseType.getCourseCost()) {
            return ERROR;
        }

        System.out.println(courseType);

        // 没有 id 就添加, 有id 就 更新
        if (!"".equals(courseType.getCourseId())) {
            courseTypeService.update(courseType);
            return SUCCESS;
        }
        courseTypeService.save(courseType);
        return SUCCESS;
    }


    /**
     * 查询所有
     *
     * @return
     */
    public String findAll() {

        // 查询所有
        if (null == courseType.getCourseName()
                && 0 == courseType.getCourseCost()
                && 0 == courseType.getTotal()
                && null == courseType.getCourseId()
                && null == courseType.getRemark()) {

            pageBean = courseTypeService.findAll(courseType, null, null, pageNum, pageSize);

        } else {

            // 条件查询

            String condition = "";
            List params = new ArrayList();

            // courseName不为空,进行参数拼接
            if (!"".equals(courseType.getCourseName())) {
                condition += "and courseName like '%"+courseType.getCourseName()+ "%' ";
            }
            // total 不为空,进行参数拼接
            if (0 != totalStart || 0 != totalEnd) {
                if (totalEnd != 0 && totalStart != 0) {
                    condition += "and total between ? and ? ";
                    params.add(totalStart);
                    params.add(totalEnd);
                }else {
                    if (totalStart == 0 && totalEnd != 0){
                        condition += "and total <= ? ";
                        params.add(totalEnd);
                    }
                    if (totalStart != 0 && totalEnd == 0){
                        condition += "and total >= ? ";
                        params.add(totalStart);
                    }
                }

            }
            // Cost 不为空,进行参数拼接
            if (0 != costStart || 0 != costEnd) {
                if (totalEnd != 0 && totalStart != 0) {
                    condition += "and courseCost between ? and ? ";
                    params.add(costStart);
                    params.add(costEnd);
                }else {
                    if (costStart == 0 && costEnd != 0){
                        condition += "and courseCost <= ? ";
                        params.add(costEnd);
                    }
                    if (costStart != 0 && costEnd == 0){
                        condition += "and courseCost >= ? ";
                        params.add(costStart);
                    }
                }

            }
            // remark不为空,进行参数拼接
            if (!"".equals(courseType.getRemark())) {
                condition += "and remark like '%" + courseType.getTotal() + "%'";
            }
            pageBean = courseTypeService.findAll(courseType, condition, params.toArray(), pageNum, pageSize);

        }


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


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public double getCostStart() {
        return costStart;
    }

    public void setCostStart(double costStart) {
        this.costStart = costStart;
    }

    public double getCostEnd() {
        return costEnd;
    }

    public void setCostEnd(double costEnd) {
        this.costEnd = costEnd;
    }

    public int getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(int totalStart) {
        this.totalStart = totalStart;
    }

    public int getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(int totalEnd) {
        this.totalEnd = totalEnd;
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
