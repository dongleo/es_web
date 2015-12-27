package com.es.webservice.dao;

import com.es.webservice.dao.base.BaseHibernateDao4;
import com.es.webservice.model.PhyIndex;
import org.springframework.stereotype.Repository;

/**
 * Created by dongYer on 15/11/27.
 */
@Repository
public class PhyIndexDao extends BaseHibernateDao4<PhyIndex, Integer> {
    public PhyIndexDao(Class<PhyIndex> entityClass) {
        super(entityClass);
    }
    public PhyIndexDao() {
        super(PhyIndex.class);
    }
}
