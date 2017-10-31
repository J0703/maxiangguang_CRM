package com.lanou.service.impl;

import com.lanou.dao.ClassesDao;
import com.lanou.domain.Classes;
import com.lanou.service.ClassesService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void save(Classes classes) {
        classesDao.save(classes);
    }

    @Override
    public void update(Classes classes) {
        classesDao.update(classes);
    }

    @Override
    public Classes findById(String classId) {
        Object[] param = {classId};
        return classesDao.findSingle("from Classes where classId=?", param);
    }
}
