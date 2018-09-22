package com.cosme.common.util;

import com.google.common.base.Splitter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-19 13:21
 **/
public class SessionUtils {

    public static final String APP_KEY = "COSME_SESSION";
    public static final int COOKIE_EXPIRE_SECOND = 60 * 60;
    public static final String PUBLIC_KEY = "39D9625FABD93D46561871240D6624C5";

    public static String getUserId(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        String cookieValue = cookieUtils.getCookieValue(APP_KEY);
        if (!StringUtils.hasText(cookieValue)) {
            return "";
        }
        String userIdAndTime = AESUtil.decrypt(cookieValue, PUBLIC_KEY);
        List<String> userIdAndTimes = Splitter.on("|").trimResults().splitToList(userIdAndTime);
        String userId = userIdAndTimes.get(0);
        Integer cookieExpireTime = Integer.valueOf(userIdAndTimes.get(1)) + COOKIE_EXPIRE_SECOND;
        if (System.currentTimeMillis() / 1000 > cookieExpireTime) {
            return "";
        }
        return userId;
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String userId) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        cookieUtils.setExpireTimeBySeconds(COOKIE_EXPIRE_SECOND);
        String encryptUserId = AESUtil.encrypt(userId + "|" + System.currentTimeMillis() / 1000, PUBLIC_KEY);
        cookieUtils.addCookie(APP_KEY, encryptUserId);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        cookieUtils.delCookie(APP_KEY);

    }

}
