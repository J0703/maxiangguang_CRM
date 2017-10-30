package com.lanou.service.impl;

import com.lanou.dao.ClassesDao;
import com.lanou.domain.Classes;
import com.lanou.service.ClassesService;

/**
 * Created by dllo on 17/10/30.
 */
public class ClassesServiceImpl extends BaseServiceImpl<Classes> implements ClassesService {

    private ClassesDao classesDao;


    public ClassesDao getClassesDao() {
        return classesDao;
    }

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }
}
