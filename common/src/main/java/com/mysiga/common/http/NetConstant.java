package com.mysiga.common.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络相关常量定义
 */

public class NetConstant {

    public static final String SUCCESS = "0";
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_NO_LOGIN = 10040;
    private static final Map<String, String> SERVERS = new HashMap<>(3);


    static {
        // 正式服务器
//        SERVERS.put(BuildConfig.RELEASE, "http://xuexi-xb.hrtop.net:8081/");
        // 上海开发服务器
//        SERVERS.put(BuildConfig.DEV_SHANGHAI, "http://10.10.1.14:8090/");
//        SERVERS.put(BuildConfig.DEV_BEIJING, "http://duty.hrtop.net:9080/");
    }


    public static final String URL_H5 = getUrl();


    /**
     * 获取通用网络url
     *
     * @return
     */
    public static String getUrl() {
//        return SERVERS.get(BuildConfig.HOST_SERVER);
        return "http://www.baidu.com";
    }

    public static String getH5BaseUrl() {
        return URL_H5;
    }

    public static final String SAVE_COOKIE = "SaveCookie";
}
