package com.es.webservice.service;

import com.es.webservice.dao.AccountDao;
import com.es.webservice.dao.PhyIndexDao;
import com.es.webservice.dto.AccountDto;
import com.es.webservice.dto.LoginResult;
import com.es.webservice.dto.PhyIndexDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.model.Account;
import com.es.webservice.model.PhyIndex;
import com.es.webservice.util.MD5Util;
import com.es.webservice.util.SysConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongYer on 15/11/25.
 */
@Service
@Transactional
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountDao accountDao;
    @Resource
    private PhyIndexDao phyIndexDao;

    public ResultBean register(AccountDto dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getPasswordCfm())) {
            return new ResultBean(false, "");
        }
        Account account = new Account();
        account.setTel(dto.getTel());
        account.setPassword(MD5Util.MD5(dto.getPassword()));
        account.setIsMain(SysConstants.IS_MAIN_YES);
        account.setIsDelete(SysConstants.IS_DELETE_NO);
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
        resultData.setHipline(account.getHipline());
        resultData.setHeight(account.getHeight());
        resultData.setScore(account.getScore());
        resultData.setScoreRatio(account.getScoreRatio());
        resultData.setToken(generateToken(account));

        PhyIndex phyIdx = phyIndexDao.queryLastedByAccountId(account.getAccountId());
        if (phyIdx != null) {
            PhyIndexDto phyIndexDto = new PhyIndexDto();
            phyIndexDto.setAccountId(account.getAccountId());
            phyIndexDto.setBmi(phyIdx.getBmi());
            phyIndexDto.setFatRatio(phyIdx.getFatRatio());
            phyIndexDto.setWeight(phyIdx.getWeight());
            resultData.setPhyIdx(phyIndexDto);
        }

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
        account.setHipline(dto.getHipline());
        account.setWaistline(dto.getWaistline());
        account.setHeight(dto.getHeight());
        account.setUpdateTime(new Date());

        if (dto.getScore() != null) {
            account.setScore(dto.getScore());
            Double scoreRatio = accountDao.queryScoreRatio(account.getScore());
            if (scoreRatio != null) {
                account.setScoreRatio(scoreRatio);
            }
        }

        ResultBean resultBean = new ResultBean(true, "");
        if (!accountDao.update(account)) {
            resultBean.setSuccess(false);
            return resultBean;
        }
        logger.info("user update. accountId: " + account.getAccountId() + ", ip: " + account.getIp());
        resultBean.setData(account);
        return resultBean;
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

    public ResultBean addNewAccount(AccountDto dto) {
        Account account = accountDao.get(dto.getParentAccountId());
        if (account == null) {
            return new ResultBean(false, "");
        }
        Account newAccount = new Account();

        newAccount.setAccountName(dto.getAccountName());
        newAccount.setGender(dto.getGender());
        newAccount.setBirth(dto.getBirth());
        newAccount.setWaistline(dto.getWaistline());
        newAccount.setHeight(dto.getHeight());
        newAccount.setUpdateTime(new Date());
        newAccount.setIsMain(SysConstants.IS_MAIN_NO);
        newAccount.setIsDelete(SysConstants.IS_DELETE_NO);
        newAccount.setParentAccountId(account.getAccountId());
        newAccount.setRegisterTime(new Date());

        newAccount = accountDao.add(newAccount);
        if (newAccount == null) {
            return new ResultBean(false, "");
        }

        ResultBean resultBean = new ResultBean(true, "");
        resultBean.setData(newAccount);
        logger.info("add new account. accountId: " + newAccount.getAccountId());
        return resultBean;
    }

    public ResultBean getAccountList(Integer parentAccountId) {
        List<Account> accountList = accountDao.queryAccountListByParentAccountId(parentAccountId);
        List<AccountDto> dtoList = new ArrayList<AccountDto>();
        if (accountList != null) {
            for (Account account : accountList) {
                AccountDto dto = new AccountDto();
                dto.setAccountId(account.getAccountId());
                dto.setAccountName(account.getAccountName());
                dto.setGender(account.getGender());
                dto.setBirth(account.getBirth());
                dto.setWaistline(account.getWaistline());
                dto.setHipline(account.getHipline());
                dto.setHeight(account.getHeight());
                dto.setIsMain(account.getIsMain());
                dto.setScore(account.getScore());
                dto.setScoreRatio(account.getScoreRatio());
                dto.setParentAccountId(account.getParentAccountId());

                dtoList.add(dto);
            }
        }
        ResultBean bean = new ResultBean(true, "");
        bean.setData(dtoList);
        return bean;
    }

    public ResultBean deleteAccount(Integer accountId, Integer parentAccountId) {
        Account parentAccount = accountDao.get(parentAccountId);
        if (parentAccount == null) {
            return new ResultBean(false, "");
        }

        Account account = accountDao.get(accountId);
        account.setIsDelete(SysConstants.IS_DELETE_YES);

        Boolean result = accountDao.update(account);

        return new ResultBean(result, "");
    }

    public boolean checkLogin(Integer accountId, String token) {
        Account account = accountDao.get(accountId);
        return account != null && StringUtils.equals(token, generateToken(account));
    }

    /**
     * generate token.
     */
    protected String generateToken(Account account) {
        return MD5Util.MD5(account.getAccountId() + "_" + account.getPassword());
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
