package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/26.
 */
@Controller("postAction")
@Scope("prototype")
public class PostAction extends ActionSupport implements ModelDriven<Post> {
    // 接收表单数据
    private Post post;
    // 承接查询到的post集合
    private List<Post> posts;

    // 二级联动装载 部门
    private List<Department> departmentList;
    private String departId;

    // 二级联动装载 职务
    private List<Post> postList;


    @Resource
    private PostService postService;
    @Resource
    private DepartmentService departmentService;

    /**
     * 查询所有部门
     *
     * @return
     */
    public String findAll() {
        posts = postService.findAll("from Post");

        for (Post post1 : posts) {
            System.out.println(post1);
        }
        return SUCCESS;
    }

    /**
     *  二级联动查询部门
     * @return
     */
    public String showDepart() {
        departmentList = departmentService.findAll("from Department");
        return SUCCESS;
    }

    /**
     *  二级联动 根据部门id查询 该部门下所有职务
     * @return
     */
    public String showPost(){

        System.out.println(departId);

        Object[] params = {departId};
        postList = postService.find("from Post where depId= ?", params);
        for (Post post1 : postList) {
            System.out.println(post1);
        }

        return SUCCESS;
    }

    /**
     * 添加和编辑
     *
     * @return
     */
    public String add() {

        System.out.println(post.getDepartment());

        // 根据departId查询部门
        Object[] params = {departId};
        Department depart = departmentService.findSingle("from Department where depId=?", params);

        // 如果postId为空, 保存
        if (post.getPostId().equals("") || post.getPostId() == null) {

            post.setDepartment(depart);
            postService.save(post);

            // 不为空, 更新
        } else {
            // 通过postId查询职务
            Object[] params1 = {post.getPostId()};
            Post post1 = postService.findSingle("from Post where postId=?", params1);

            // 如果 前台职务id 和 数据库查 出来的职务 id 相同就进行 更新
            if (post1.getPostId().equals(post.getPostId()) && post1.getDepartment().getDepId().equals(depart.getDepId())) {
                post1.setPostName(post.getPostName());
                post1.setDepartment(depart);
                postService.update(post1);

            }else {
            // 从编辑位置添加部门
                post.setDepartment(depart);
                postService.save(post);
            }


        }

        return SUCCESS;
    }


    @Override
    public Post getModel() {
        post = new Post();
        return post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }


    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
