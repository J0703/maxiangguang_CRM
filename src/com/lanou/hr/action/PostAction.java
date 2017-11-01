package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.common.domain.PageBean;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
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

    private int pageNum;
    private PageBean<Post> pageBean;

    // 每页显示记录数
    private int pageSize = 4;


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
        posts = postService.findAll();
        return SUCCESS;
    }

    /**
     *  分页查询
     */
    public String findAllPost(){

        pageBean = postService.findAll(post, null, null, pageNum, pageSize);

        return SUCCESS;
    }

    /**
     *  二级联动查询部门
     * @return
     */
    public String showDepart() {
        departmentList = departmentService.findAll();
        return SUCCESS;
    }

    /**
     *  二级联动 根据部门id查询 该部门下所有职务
     * @return
     */
    public String showPost(){

        System.out.println(departId);

        postList = postService.findByDepId(departId);

        return SUCCESS;
    }

    /**
     * 添加 和 编辑
     *
     * @return
     */
    public String add() {

        if (StringUtils.isBlank(post.getPostName())){
            addActionError("职务名不能为空!");
            return ERROR;
        }

        // 根据departId查询部门
        Department depart = departmentService.findById(departId);

        System.out.println(post.getPostId());
        System.out.println("******");

        // 如果postId为空, 保存
        if ("".equals(post.getPostId())) {

            post.setDepartment(depart);
            postService.save(post);

            // 不为空, 更新
        } else {
            // 通过postId查询职务
            Post post1 = postService.findById(post.getPostId());

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public PageBean<Post> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Post> pageBean) {
        this.pageBean = pageBean;
    }
}
