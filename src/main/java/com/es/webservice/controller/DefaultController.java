package com.es.webservice.controller;

import com.es.webservice.dto.AccountDto;
import com.es.webservice.dto.PhyIndexDto;
import com.es.webservice.dto.ResultBean;
import com.es.webservice.service.AccountService;
import com.es.webservice.service.PhyIndexService;
import com.es.webservice.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class DefaultController {
    @Resource
    private AccountService accountService;
    @Resource
    private PhyIndexService phyIndexService;

    @RequestMapping("register")
    @ResponseBody
    public ResultBean register(HttpServletRequest request,
                               @RequestBody AccountDto dto) {
        dto.setIp(RequestUtil.getIp(request));
        return accountService.register(dto);
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(HttpServletRequest request,
                             @RequestBody AccountDto dto) {
        dto.setIp(RequestUtil.getIp(request));
        return accountService.login(dto);
    }

    @RequestMapping("edit")
    @ResponseBody
    public ResultBean edit(HttpServletRequest request,
                            @RequestBody AccountDto dto) {
        dto.setIp(RequestUtil.getIp(request));
        return accountService.edit(dto);
    }

    @RequestMapping("addNewAccount")
    @ResponseBody
    public ResultBean addNewAccount(HttpServletRequest request,
                                    @RequestBody AccountDto dto) {
        dto.setIp(RequestUtil.getIp(request));
        return accountService.addNewAccount(dto);
    }

    @RequestMapping("getAccountList")
    @ResponseBody
    public ResultBean getAccountList(HttpServletRequest request,
                                    @RequestParam Integer parentAccountId) {
        return accountService.getAccountList(parentAccountId);
    }

    @RequestMapping("delete/{accountId}")
    @ResponseBody
    public ResultBean deleteAccount(@PathVariable Integer accountId, @RequestParam Integer parentAccountId) {
        return accountService.deleteAccount(accountId, parentAccountId);
    }

    @RequestMapping("passwd/modify")
    @ResponseBody
    public ResultBean modifyPasswd(HttpServletRequest request,
                                    @RequestBody AccountDto dto) {
        dto.setIp(RequestUtil.getIp(request));
        return accountService.modifyPasswd(dto);
    }

    @RequestMapping("phy/submit")
    @ResponseBody
    public ResultBean submitPhy(@RequestBody PhyIndexDto dto) {
        return phyIndexService.submit(dto);
    }
}
