package com.cosme.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.cosme.common.StateCode;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.util.SessionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Tanlian
 * @create 2018-08-19 13:17
 **/
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/json;charset=utf-8");
        String userId = SessionUtils.getUserId(request, response);
        if (!StringUtils.hasText(userId)) {
            ResultDTO resultDTO = ResultDTO.fail(StateCode.USER_OFFLINE, "用户未登录");
            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(resultDTO));
            writer.close();
            return false;
        }
        return true;
    }
}
