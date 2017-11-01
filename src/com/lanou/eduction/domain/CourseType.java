package com.lanou.eduction.domain;

/**
 * Created by dllo on 17/10/27.
 */
public class CourseType {
    private String courseId;
    private String courseName;
    private double courseCost;//学费
    private int total;// 学时
    private String remark;

    public CourseType() {
    }

    public CourseType(String courseName, double courseCost, int total, String remark) {
        this.courseName = courseName;
        this.courseCost = courseCost;
        this.total = total;
        this.remark = remark;
    }

    public CourseType(String courseId, String courseName, double courseCost, int total, String remark) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCost = courseCost;
        this.total = total;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "courseTypeId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCost=" + courseCost +
                ", total=" + total +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseTypeId) {
        this.courseId = courseTypeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(double courseCost) {
        this.courseCost = courseCost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
