package com.cosme.back.controller;

import com.cosme.back.service.UserService;
import com.cosme.common.util.SessionUtils;
import com.cosme.dto.UserDTO;
import com.cosme.param.UserQueryParam;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-19 18:14
 **/
@RestController
public class BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前用户id
     *
     * @param request
     * @param response
     * @return
     */
    protected String getOnlineUserId(HttpServletRequest request, HttpServletResponse response) {
        return SessionUtils.getUserId(request, response);
    }

    /**
     * 获取当前用户信息
     *
     * @param request
     * @param response
     * @return
     */
    protected UserDTO getOnLineUserDetail(HttpServletRequest request, HttpServletResponse response) {
        String userId = SessionUtils.getUserId(request, response);
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setUserIds(Sets.newHashSet(userId));
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        UserDTO userDTO = userDTOs.get(0);
        return userDTO;
    }
}
