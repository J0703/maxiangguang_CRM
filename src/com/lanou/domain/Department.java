package com.lanou.domain;

import java.util.List;

/**
 * Created by dllo on 17/10/24.
 */
public class Department {
    private String  depId;// 主键id
    private String depName;//部门名称
    private List<Post> posts;

    public Department() {
    }

    public Department(String depName) {
        this.depName = depName;
    }

    public Department(String depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                ", posts=" + posts +
                '}';
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
