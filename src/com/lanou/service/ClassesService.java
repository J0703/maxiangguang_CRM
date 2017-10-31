package com.lanou.service;

import com.lanou.domain.Classes;

/**
 * Created by dllo on 17/10/30.
 */
public interface ClassesService extends BaseService<Classes> {


    void save(Classes classes);

    void update(Classes classes);

    Classes findById(String classId);
}
