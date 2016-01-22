package com.es.webservice;

import com.es.webservice.dto.AccountDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by dongYer on 15/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml",
        "classpath:spring-dataSource.xml"})
public class AccountServiceTest {
    @Resource
    private AccountService accountService;

    @Test
    @Rollback(false)
    public void addTest() {
        AccountDto account = new AccountDto();
        account.setTel("18501650906");
        account.setPassword("hehe");
        account.setPasswordCfm("hehe");
        account.setIp("127.0.0.1");

        ResultBean result = accountService.register(account);
        assert result.isSuccess();
    }
}
