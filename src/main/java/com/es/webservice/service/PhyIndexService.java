package com.es.webservice.service;

import com.es.webservice.dao.AccountDao;
import com.es.webservice.dao.PhyIndexDao;
import com.es.webservice.dto.PhyIndexDto;
import com.es.webservice.dto.QueryHistoryRequestDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.model.Account;
import com.es.webservice.model.PhyIndex;
import com.es.webservice.model.PhyIndexHistory;
import com.es.webservice.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
                account.setScoreRatio(scoreRatio);
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

    public ResultBean queryHistory(QueryHistoryRequestDto requestDto) {
        ResultBean resultBean = new ResultBean(true, "");
        String mode = "%Y-%m-%d";
        String format = "yyyy-MM-dd";
        if ("MONTH".equals(requestDto.getMode())) {
            mode = "%Y-%m";
            format = "yyyy-MM";
        }
        List<PhyIndexHistory> indexList = phyIndexDao.queryPhyIdxList(requestDto.getAccountId(),
                requestDto.getStartDate(), requestDto.getEndDate(), mode);
        List<PhyIndexDto> dtoList = new ArrayList<>();
        Map<String, PhyIndexHistory> historyMap = new HashMap<>();
        if (indexList != null && !indexList.isEmpty()) {
            for (PhyIndexHistory index : indexList) {
                historyMap.put(index.getSubmitTime(), index);
            }
        }
        Date date = requestDto.getStartDate();
        PhyIndexHistory history;
        while (date.before(requestDto.getEndDate())) {
            String key = DateUtil.format(date, format);
            PhyIndexDto dto = new PhyIndexDto();
            dto.setDate(key);

            history = historyMap.get(key);
            if (history != null) {
                dto.setWeight(history.getWeight());
                dto.setBmi(history.getBmi());
                dto.setFatRatio(history.getFatRatio());
            }
            dtoList.add(dto);

            date = addDate(date, mode);
        }
        resultBean.setData(dtoList);
        return resultBean;
    }

    protected Date addDate(Date date, String mode) {
        if ("MONTH".equals(mode)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date.getTime());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            return calendar.getTime();
        }
        return new Date(date.getTime() + 86400 * 1000);
    }
}
