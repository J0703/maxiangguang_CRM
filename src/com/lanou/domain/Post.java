package com.lanou.domain;

import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
public class Post {
    private String postId;
    private String postName;
    private Department department;
    private List<Staff> staffs;

    public Post() {
    }


    public Post(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

    public Post(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", department=" + department +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }
}
