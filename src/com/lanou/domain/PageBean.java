package com.lanou.domain;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class PageBean<T> {

    private int pageNum; // 当前页码
    private int pageSize; // 每页记录数
    private int totalRecord; // 总记录数
    private int startIndex; // 开始索引
    private int totalPage; // 总页码数

    private List<T> data; // 当前页记录

    public PageBean() {
    }


    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
    }

    public PageBean(int pageNum, int pageSize, int totalRecord, int totalPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
    }

    public PageBean(int pageNum, int pageSize, int totalRecord, int startIndex, int totalPage, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.startIndex = startIndex;
        this.totalPage = totalPage;
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
