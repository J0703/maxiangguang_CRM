package com.lanou.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dllo on 17/10/25.
 */
public class Staff {

    private String staffId;//用户id
    private String loginName;//登录名
    private String loginPwd;//密码
    private String staffName;//员工姓名
    private String gender;//性别
    private Timestamp onDutyDate;//入职时间
    private Post post;//职务
    private Department department;

    public Staff() {
    }

    public Staff(String loginName, String loginPwd, String staffName, String gender) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.staffName = staffName;
        this.gender = gender;
    }

    public Staff(String loginName, String loginPwd, String staffName, String gender, Timestamp onDutyDate, Post post) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.staffName = staffName;
        this.gender = gender;
        this.onDutyDate = onDutyDate;
        this.post = post;
    }

    public Staff(String staffId, String loginName, String loginPwd, String staffName, String gender, Timestamp onDutyDate, Post post) {
        this.staffId = staffId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.staffName = staffName;
        this.gender = gender;
        this.onDutyDate = onDutyDate;
        this.post = post;
    }

    public Staff(String staffId, String loginName, String loginPwd, String staffName, String gender, Timestamp onDutyDate, Post post, Department department) {
        this.staffId = staffId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.staffName = staffName;
        this.gender = gender;
        this.onDutyDate = onDutyDate;
        this.post = post;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", staffName='" + staffName + '\'' +
                ", gender='" + gender + '\'' +
                ", onDutyDate=" + onDutyDate +
                ", post=" + post +
                ", department=" + department +
                '}';
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getOnDutyDate() {
        return onDutyDate;
    }

    public void setOnDutyDate(Timestamp onDutyDate) {
        this.onDutyDate = onDutyDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
