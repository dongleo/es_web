package com.es.webservice.dao;

import com.es.webservice.dao.base.BaseHibernateDao4;
import com.es.webservice.model.Account;
import com.es.webservice.util.SysConstants;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongYer on 15/11/25.
 */
@Repository("accountDao")
public class AccountDao extends BaseHibernateDao4<Account, Integer> {
    public AccountDao(Class<Account> entityClass) {
        super(entityClass);
    }

    public AccountDao() {
        super(Account.class);
    }

    public Account queryAccountByTel(String tel) {
        String hql = "from Account a where a.tel = :tel and a.isDelete <> :isDelete";
        Map<String, Object> params = new HashMap<>();
        params.put("tel", tel);
        params.put("isDelete", SysConstants.IS_DELETE_YES);
        List<Account> accountList = this.find(hql, params);
        if (accountList != null && accountList.size() > 0) {
            return accountList.get(0);
        }
        return null;
    }

    public List<Account> queryAccountListByParentAccountId(Integer accountId) {
        String hql = "from Account a where (a.accountId = :accountId or a.parentAccountId = :accountId) and a.isDelete <> :isDelete";
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);
        params.put("isDelete", SysConstants.IS_DELETE_YES);
        return this.find(hql, params);
    }
}
