package com.es.webservice.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongYer on 15/11/25.
 */
public class RequestUtil {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getParameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        return param == null ? "" : param;
    }
}
