package com.lanou.service;


import com.lanou.domain.PageBean;
import com.lanou.domain.Post;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService extends BaseService<Post>{

    /**
     *  保存某个对象
     * @return  保存之后的对象
     */
    void save(Post post);

    /**
     *  删除某个对象
     *
     */
    void delete(Post post);

    /**
     *  更改某个对象
     *
     */
    void update(Post post);

    /**
     * 查询所有
     * @return
     */
    List<Post> findAll();

    /**
     *  根据条件查询, 返回查询到的结果集合
     * @return 查询到的结果集合
     */
    Post findByName(String postName);

    /**
     *  查询单个
     * @return
     */
    Post findById(String postId);

    List<Post> findByDepId(String depId);

    Post get(Serializable id);

}
