package com.es.webservice.interceptor;

import com.es.webservice.service.AccountService;
import com.es.webservice.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongYer on 15/11/25.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        String ip = RequestUtil.getIp(request);
        logger.info("ip: " + ip + ", request url: " + url);

        // TODO login check
        Integer accountId = Integer.parseInt(RequestUtil.getParameter(request, "accountId"));
        String token = RequestUtil.getParameter(request, "token");
        if (accountId == null || token == null) {
            // TODO 返回错误json字符串
            return false;
        }
        if (accountService.checkLogin(accountId, token)) {
            // TODO 返回错误json字符串
            return false;
        }
        /*if (!flag) {
            T_supplier_user user = LoginController.getLoginUser(request);
            if (user != null) flag = true;
        }*/
        return true;
    }
}
