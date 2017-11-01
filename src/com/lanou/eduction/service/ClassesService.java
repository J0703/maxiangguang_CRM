package com.lanou.eduction.service;

import com.lanou.eduction.domain.Classes;
import com.lanou.common.service.BaseService;

/**
 * Created by dllo on 17/10/30.
 */
public interface ClassesService extends BaseService<Classes> {


    void save(Classes classes);

    void update(Classes classes);

    Classes findById(String classId);
}
