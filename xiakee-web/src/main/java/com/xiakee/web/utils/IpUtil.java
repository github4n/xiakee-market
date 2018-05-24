package com.xiakee.web.utils;

import org.apache.log4j.Logger;

import java.net.*;
import java.util.Enumeration;

/**
 * User: 刘卫程
 * Email: bjliuweicheng@jd.com
 * Date: 13-12-23
 * Time: 下午2:33
 * Description:
 */
public abstract class IpUtil {
    private static final Logger logger = Logger.getLogger(IpUtil.class);
    private static InetAddress LocalIP = null;

    //取得本机IP地址
    public static InetAddress getLocalIp() {
        try {
            LocalIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("取IP错误！",e);
        }
        return LocalIP;
    }


    /**
     * 获取本地IP地址
     *
     * @return
     */
    public static String getIp() {
        try {
            LocalIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("取IP错误！",e);
        }
        return LocalIP.toString();
    }

    public static String getFirstNonLoopBackAddress(boolean preferIpv4, boolean preferIPv6) {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface i = (NetworkInterface) en.nextElement();
                for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements(); ) {
                    InetAddress addr = (InetAddress) en2.nextElement();
                    if (!addr.isLoopbackAddress()) {
                        if (addr instanceof Inet4Address) {
                            if (preferIPv6) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                        if (addr instanceof Inet6Address) {
                            if (preferIpv4) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getFirstNonLoopBackAddress(true, false));
    }
}
