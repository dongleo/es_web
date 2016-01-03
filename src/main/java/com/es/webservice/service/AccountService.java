package com.es.webservice.service;

import com.es.webservice.dao.AccountDao;
import com.es.webservice.dto.AccountDto;
import com.es.webservice.dto.LoginResult;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.model.Account;
import com.es.webservice.util.MD5Util;
import com.es.webservice.util.SysConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dongYer on 15/11/25.
 */
@Service
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountDao accountDao;

    public ResultBean register(AccountDto dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getPasswordCfm())) {
            return new ResultBean(false, "");
        }
        Account account = new Account();
        account.setTel(dto.getTel());
        account.setPassword(MD5Util.MD5(dto.getPassword()));
        account.setIsMain(SysConstants.IS_MAIN_YES);
        account.setIp(dto.getIp());
        account.setRegisterTime(new Date());
        logger.info("new user register success. tel: " + account.getTel() + ", ip: " + account.getIp());
        account = accountDao.add(account);
        if (account == null) {
            return new ResultBean(false, "");
        }
        return new ResultBean(true, "");
    }

    public ResultBean login(AccountDto dto) {
        Account account = accountDao.queryAccountByTel(dto.getTel());
        if (account == null) {
            return new ResultBean(false, "");
        }
        String passwdMd5 = MD5Util.MD5(dto.getPassword());
        if (!StringUtils.equals(account.getPassword(), passwdMd5)) {
            return new ResultBean(false, "");
        }
        ResultBean resultBean = new ResultBean(true, "");
        LoginResult resultData = new LoginResult();
        resultData.setAccountId(account.getAccountId());
        resultData.setAccountName(account.getAccountName());
        resultData.setTel(account.getTel());
        resultData.setGender(account.getGender());
        resultData.setBirth(account.getBirth());
        resultData.setWaistline(account.getWaistline());
        resultData.setHeight(account.getHeight());
        resultData.setToken(generateToken(account));
        resultBean.setData(resultData);

        account.setLastLoginTime(new Date());
        accountDao.update(account);
        logger.info("user login. accountId: " + account.getAccountId() + ", ip: " + account.getIp());
        return resultBean;
    }

    public ResultBean edit(AccountDto dto) {
        Account account = accountDao.get(dto.getAccountId());
        if (account == null) {
            return new ResultBean(false, "");
        }
        account.setAccountName(dto.getAccountName());
        account.setGender(dto.getGender());
        account.setBirth(dto.getBirth());
        account.setWaistline(dto.getWaistline());
        account.setHeight(dto.getHeight());
        account.setUpdateTime(new Date());

        if (!accountDao.update(account)) {
            return new ResultBean(false, "");
        }
        logger.info("user update. accountId: " + account.getAccountId() + ", ip: " + account.getIp());
        return new ResultBean(true, "");
    }

    public ResultBean modifyPasswd(AccountDto dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getPasswordCfm())) {
            return new ResultBean(false, "");
        }
        Account account = accountDao.get(dto.getAccountId());
        if (account == null) {
            return new ResultBean(false, "");
        }
        account.setPassword(dto.getPassword());
        account.setUpdateTime(new Date());

        if (!accountDao.update(account)) {
            return new ResultBean(false, "");
        }
        logger.info("user password changed. accountId: " + account.getAccountId() + ", ip: " + account.getIp());
        return new ResultBean(true, "");
    }

    public boolean checkLogin(Integer accountId, String token) {
        Account account = accountDao.get(accountId);
        return account != null && StringUtils.equals(token, generateToken(account));
    }

    /**
     * generate token.
     */
    protected String generateToken(Account account) {
        return MD5Util.MD5(account.getAccountName() + "_" + account.getPassword());
    }
}
