package com.es.webservice;

import com.es.webservice.dto.PhyIndexDto;
import com.es.webservice.dto.QueryHistoryRequestDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.service.PhyIndexService;
import com.es.webservice.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dongYer on 16/1/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml",
        "classpath:spring-dataSource.xml"})
public class PhyIdxServiceTest {
    @Resource
    private PhyIndexService phyIndexService;

    @Test
    @Rollback(false)
    public void submitTest() {
        PhyIndexDto dto = new PhyIndexDto();
        dto.setScore(90.0);
        dto.setWeight(65.5);
        dto.setFatRatio(23.4);
        dto.setBmi(23d);
        dto.setAccountId(1);
        ResultBean result = phyIndexService.submit(dto);

        assert result.isSuccess();
    }

    @Test
    @Rollback(false)
    public void queryHistoryTest() {
        Date startDate = DateUtil.parseDate("2015-12-01");
        Date endDate = DateUtil.parseDate("2016-12-01");
        QueryHistoryRequestDto requestDto = new QueryHistoryRequestDto();
        requestDto.setAccountId(1);
        requestDto.setStartDate(startDate);
        requestDto.setEndDate(endDate);
        ResultBean result = phyIndexService.queryHistory(requestDto);

        assert result.isSuccess();
    }
}
