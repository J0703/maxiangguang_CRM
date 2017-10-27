package com.lanou.service.impl;

import com.lanou.dao.PostDao;
import com.lanou.dao.StaffDao;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.StaffService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;
    private PostDao postDao;

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void delete(Staff staff) {
        staffDao.delete(staff);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffDao.findAll("from Staff");
    }

    @Override
    public Staff findByStaffId(String staffId) {
        Object[] param = {staffId};
        return staffDao.findSingle("from Staff where staffId=?", param);
    }

    @Override
    public List<Staff> findByPostId(String postId) {
        Object[] param = {postId};
        return staffDao.find("from Staff where postId=?", param);
    }

    /**
     *  通过 depId 查询
     * @param depId
     * @return
     */
    @Override
    public List<Staff> findByDepId(String depId) {
        Object[] param = {depId};
        List<Post> posts = postDao.find("from Post where depId =?", param);
        List<Staff> p = new ArrayList<>();
        for (Post post : posts) {
            Object[] param2 = {post.getPostId()};
            List<Staff> staffs = staffDao.find("from Staff where postId=?", param2);
            for (Staff staff : staffs) {
                p.add(staff);
            }
        }
        return p;
    }


    @Override
    public List<Staff> findByStaffName(String staffName) {
        Object[] param = {staffName};
        return staffDao.find("from Staff where staffName like'%?%'", param);
    }

    @Override
    public List<Staff> find(String depId, String postId, String staffName) {


        String hql = "from Staff where 1=1";
        List<String> params = new ArrayList<>();
//        if ( depId != null || !depId.equals("")){
//            params.add(depId);
//            hql += " and depId=?";
//        }
        if (!postId.equals("") || postId != null) {
            params.add(postId);
            hql += " and postId=?";
        }
        if (staffName != null || !staffName.equals("")) {
            params.add(staffName);
            hql += " and staffId=?";
        }

        return staffDao.find(hql, params.toArray());
    }

    @Override
    public Staff findSingle(String hql, Object[] params) {
        return null;
    }

    @Override
    public Staff get(Serializable id) {
        return staffDao.get(Staff.class, id);
    }

    @Override
    public Staff login(Staff staff) {
        String hql = "from Staff where loginName=? and loginPwd=?";
        Object[] params ={staff.getLoginName(), staff.getLoginPwd()};
        Staff s = (Staff) getStaffDao().findSingle(hql, params);
        System.out.println(s);
        return s;
    }

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
