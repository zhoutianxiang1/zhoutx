package com.visionet.ztx.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	/**
	 * request.getHeader("x-forwarded-for")：获取真实的ip。
	 * request.getRemoteAddr()这种方式在大部分情况下是有效的，但是通过反向代理就不能获得客户端的真是ip地址了。
	 * 增加了x-forwarded-for信息，用来跟踪原有客户端IP地址和原来客户端请求的服务端地址。
	 * @param request
	 * @return
	 */
    public static String getIpAddr(HttpServletRequest request)  {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
     }  

}
