package com.lanou.dao.impl;

import com.lanou.dao.ClassesDao;
import com.lanou.domain.Classes;
import org.hibernate.Session;

/**
 * Created by dllo on 17/10/30.
 */
public class ClassesDaoImpl extends BaseDaoImpl<Classes> implements ClassesDao{

    @Override
    public void update(Classes classes) {
        Session session = currentSession();
        session.update(classes);
    }
}
