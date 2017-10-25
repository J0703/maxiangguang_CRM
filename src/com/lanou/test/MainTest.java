package com.lanou.test;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.StaffService;
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

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        staffService = (StaffService) context.getBean("staffService");
    }

    @Test
    public void save(){

        Department department = new Department("人力资源部");
        Post post = new Post("部长");
        Staff staff = new Staff("mxg", "123", "马祥光", "男");

        department.getPosts().add(post);
        post.setDepartment(department);
        post.getStaffs().add(staff);
        staff.setPost(post);

        staffService.save(staff);


    }


}
