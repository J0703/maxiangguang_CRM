package com.lanou.domain;

/**
 * Created by dllo on 17/10/24.
 */
public class Department {
    private String  depId;// 主键id
    private String depName;//部门名称

    public Department() {
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
}
