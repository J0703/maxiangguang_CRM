package com.lanou.util.page;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class PageBean<T> {

    private int pc; // 当前页码 pageCode
    private int tp; // 总页码数 totalPage
    private int tr; // 总记录数 totalRecord
    private int ps; // 每页记录数 pageSize
    private int si; // 开始索引 startIndex
    private List<T> beanList ; // 当前页记录
    private String url;  // url 后面的条件

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    /*
        计算页数
     */
    public int getTp() {
        // 总记录数 tr / 每页记录数ps
        int tp = tr/ps;
        return tr%ps == 0 ? tp : tp + 1;

    }

    public int getSi() {
        return si;
    }

    public void setSi(int si) {
        this.si = si;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
