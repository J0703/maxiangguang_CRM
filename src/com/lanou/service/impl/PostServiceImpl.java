package com.lanou.service.impl;

import com.lanou.dao.PostDao;
import com.lanou.domain.PageBean;
import com.lanou.domain.Post;
import com.lanou.service.PostService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

    private PostDao postDao;

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void delete(Post post) {
        postDao.delete(post);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll("from Post");
    }

    @Override
    public Post findByName(String postName) {
        Object[] param = {postName};
        return postDao.findSingle("from Post where postName=?", param);
    }

    @Override
    public Post findById(String postId) {
        Object[] param ={postId};
        return postDao.findSingle("from Post where postId=?", param);
    }

    @Override
    public List<Post> findByDepId(String depId) {
        Object[] param = {depId};
        return postDao.find("from Post where depId=?", param);
    }

    @Override
    public Post get(Serializable id) {
        return postDao.get(Post.class, id);
    }



    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
