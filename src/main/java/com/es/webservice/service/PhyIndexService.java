package com.es.webservice.service;

import com.es.webservice.dao.AccountDao;
import com.es.webservice.dao.PhyIndexDao;
import com.es.webservice.dto.PhyIndexDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.model.Account;
import com.es.webservice.model.PhyIndex;
import com.es.webservice.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dongYer on 15/11/27.
 */
@Service
public class PhyIndexService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PhyIndexDao phyIndexDao;
    @Resource
    private AccountDao accountDao;

    public ResultBean submit(PhyIndexDto dto) {
        Account account = accountDao.get(dto.getAccountId());
        if (account == null) {
            return new ResultBean(false, "");
        }
        PhyIndex index = new PhyIndex();
        index.setWeight(dto.getWeight());
        index.setFatRatio(dto.getFatRatio());
        index.setAccountId(account.getAccountId());
        index.setAge(DateUtil.getAgeByBirthday(account.getBirth()));
        index.setWaistline(account.getWaistline());
        index.setHeight(account.getHeight());
        index.setSubmitTime(new Date());

        index = phyIndexDao.add(index);
        if (index == null) {
            return new ResultBean(false, "");
        }
        logger.info("user submit phy index. accountId: " + account.getAccountId());
        return new ResultBean(true, "");
    }
}
