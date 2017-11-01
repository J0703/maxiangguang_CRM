package com.lanou.eduction.service.impl;

import com.lanou.eduction.dao.ClassesDao;
import com.lanou.eduction.domain.Classes;
import com.lanou.eduction.service.ClassesService;
import com.lanou.common.service.impl.BaseServiceImpl;

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
