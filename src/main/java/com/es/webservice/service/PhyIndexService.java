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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongYer on 15/11/27.
 */
@Service
@Transactional
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
        index.setBmi(dto.getBmi());
        index.setFatRatio(dto.getFatRatio());
        index.setAccountId(account.getAccountId());
        index.setAge(DateUtil.getAgeByBirthday(account.getBirth()));
        index.setWaistline(account.getWaistline());
        index.setHipline(account.getHipline());
        index.setHeight(account.getHeight());
        index.setSubmitTime(new Date());

        if (dto.getScore() != null) {
            index.setScore(dto.getScore());
            account.setScore(dto.getScore());

            Double scoreRatio = accountDao.queryScoreRatio(index.getScore());
            if (scoreRatio != null) {
                index.setScoreRatio(scoreRatio);
                account.setScore(scoreRatio);
            }
        }

        ResultBean resultBean = new ResultBean(true, "");
        index = phyIndexDao.add(index);
        accountDao.update(account);
        if (index == null) {
            resultBean.setSuccess(false);
            return resultBean;
        }
        logger.info("user submit phy index. accountId: " + account.getAccountId());
        resultBean.setData(index);
        return resultBean;
    }

    public ResultBean queryHistory(Integer accountId, Date startDate, Date endDate) {
        ResultBean resultBean = new ResultBean(true, "");
        List<PhyIndex> indexList = phyIndexDao.queryPhyIdxList(accountId, startDate, endDate);
        List<PhyIndexDto> dtoList = new ArrayList<>();
        if (indexList != null && !indexList.isEmpty()) {
            for (PhyIndex index : indexList) {
                PhyIndexDto dto  = new PhyIndexDto();
                dto.setAccountId(index.getAccountId());
                dto.setScore(index.getScore());
                dto.setBmi(index.getBmi());
                dto.setFatRatio(index.getFatRatio());
                dto.setScoreRatio(index.getScoreRatio());
                dto.setWeight(index.getWeight());
                dto.setDate(DateUtil.format(index.getSubmitTime()));

                dtoList.add(dto);
            }
        }
        resultBean.setData(dtoList);
        return resultBean;
    }
}
