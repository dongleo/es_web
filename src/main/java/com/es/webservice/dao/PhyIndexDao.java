package com.es.webservice.dao;

import com.es.webservice.dao.base.BaseHibernateDao4;
import com.es.webservice.model.PhyIndex;
import com.es.webservice.util.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    public PhyIndex queryLastedByAccountId(Integer accountId) {
        String hql = "from PhyIndex p where p.accountId = :accountId order by p.submitTime desc";
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        setParamMap(query, params);
        query.setMaxResults(1);

        List<PhyIndex> phyIndexList = query.list();
        if (phyIndexList != null && phyIndexList.size() > 0) {
            return phyIndexList.get(0);
        }
        return null;
    }

    /**
     * select * from t_phy_idx t where (t.account_id, t.`ID`, DATE_FORMAT(t.SUBMIT_TIME, '%Y-%m-%d')) in
     * (select p.account_id, p.`ID`, DATE_FORMAT(p.SUBMIT_TIME, '%Y-%m-%d') from t_phy_idx p
     * where p.account_id = 6 and p.SUBMIT_TIME > '2015-01-01' group by DATE_FORMAT(p.SUBMIT_TIME, '%Y-%m-%d'));
     */
    public List<PhyIndex> queryPhyIdxList(Integer accountId, Date startDate, Date endDate) {
        String sql = "select * from t_phy_idx t where (t.account_id, t.`ID`, DATE_FORMAT(t.SUBMIT_TIME, '%Y-%m-%d')) in" +
                "(select p.account_id, p.`ID`, DATE_FORMAT(p.SUBMIT_TIME, '%Y-%m-%d') from t_phy_idx p " +
                "where p.account_id = :accountId and p.SUBMIT_TIME >= :startDate and p.SUBMIT_TIME < :endDate group by DATE_FORMAT(p.SUBMIT_TIME, '%Y-%m-%d'));";

        Query query = getSession().createSQLQuery(sql).addEntity(PhyIndex.class);
//        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        query.setParameter("accountId", accountId);
        query.setParameter("startDate", DateUtil.format(startDate));
        query.setParameter("endDate", DateUtil.format(endDate));

        return (List<PhyIndex>) query.list();
    }

    /*private PhyIndex parseIndex(Map map) {
        PhyIndex index = new PhyIndex();

        index.setId((Integer) map.get("ID"));
        index.setAccountId((Integer) map.get("ACCOUNT_ID"));
        index.setAge((Integer) map.get("AGE"));
        index.setWaistline((Double) map.get("WAISTLINE"));
        index.setHipline((Double) map.get("HIPLINE"));
        index.setHeight((Integer) map.get("HEIGHT"));
        index.setWeight((Double) map.get("BMI"));
        index.setFatRatio((Double) map.get("FAT_RATIO"));
        index.setScore((Double) map.get("SCORE"));
        index.setScoreRatio((Double) map.get("SCORE_RATIO"));
        index.setSubmitTime((Date) map.get("SUBMIT_TIME"));
        index.setIsValid((Integer) map.get("IS_VALID"));
        index.setUpdateTime((Date) map.get("UPDATE_TIME"));

        return index;
    }*/
}
