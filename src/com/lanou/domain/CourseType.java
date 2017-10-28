package com.lanou.domain;

/**
 * Created by dllo on 17/10/27.
 */
public class CourseType {
    private String courseTypeId;
    private String courseName;
    private double courseCost;
    private int total;
    private String remark;

    public CourseType() {
    }

    public CourseType(String courseName, double courseCost, int total, String remark) {
        this.courseName = courseName;
        this.courseCost = courseCost;
        this.total = total;
        this.remark = remark;
    }

    public CourseType(String courseTypeId, String courseName, double courseCost, int total, String remark) {
        this.courseTypeId = courseTypeId;
        this.courseName = courseName;
        this.courseCost = courseCost;
        this.total = total;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "courseTypeId='" + courseTypeId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCost=" + courseCost +
                ", total=" + total +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
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
