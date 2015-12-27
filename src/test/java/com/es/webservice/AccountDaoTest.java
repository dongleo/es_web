package com.es.webservice;

import com.es.webservice.dao.AccountDao;
import com.es.webservice.model.Account;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dongYer on 15/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml",
        "classpath:spring-dataSource.xml"})
public class AccountDaoTest {
    @Resource
    private AccountDao accountDao;

    @Test
    public void addTest() {
        Account account = new Account();
        account.setTel("18501650906");
        account.setPassword("hehe");
        account.setIp("127.0.0.1");
        account.setRegisterTime(new Date());
        account.setLastLoginTime(new Date());

        account = accountDao.autoSave(account);
        assert account.getAccountId() != null;
    }
}
