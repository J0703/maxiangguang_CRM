package com.lanou.eduction.dao;

import com.lanou.common.dao.BaseDao;
import com.lanou.eduction.domain.Classes;

/**
 * Created by dllo on 17/10/30.
 */
public interface ClassesDao extends BaseDao<Classes> {

    void update(Classes classes);
}
