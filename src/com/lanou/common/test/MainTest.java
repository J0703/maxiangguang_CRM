package com.lanou.common.test;

import com.lanou.hr.domain.Department;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.lanou.hr.service.StaffService;
import com.lanou.hr.util.EncryptUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class MainTest {


    private ApplicationContext context;
    private StaffService staffService;
    private PostService postService;
    private DepartmentService departmentService;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        staffService = (StaffService) context.getBean("staffService");
        postService = (PostService) context.getBean("postService");
        departmentService = (DepartmentService) context.getBean("departmentService");
    }



    @Test
    public void addPost(){

        Post post = new Post("部长");

        List<Department> all = departmentService.findAll();
        for (Department department : all) {
            System.out.println(department);
        }

    }

    @Test
    public void encrypt(){
        String pwd = "123";
        String s = EncryptUtil.getMD5Value(pwd);
        System.out.println(s);

    }

    @Test
    public void find(){

    }



}
