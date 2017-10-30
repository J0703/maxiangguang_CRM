package com.lanou.action;

import com.lanou.service.ClassesService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("classesAction")
@Scope("prototype")
public class ClassesAction {

    @Resource
    private ClassesService classesService;
}
