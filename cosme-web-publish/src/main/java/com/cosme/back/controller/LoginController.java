package com.cosme.back.controller;

import com.cosme.back.adapter.UserAdapter;
import com.cosme.back.service.UserService;
import com.cosme.common.constant.StateCode;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.util.AESUtil;
import com.cosme.common.util.SessionUtils;
import com.cosme.dto.UserDTO;
import com.cosme.param.UserQueryParam;
import com.cosme.request.UserRequest;
import com.cosme.vo.UserVO;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-19 15:22
 **/
@RestController
@RequestMapping("/cm/")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAdapter userAdapter;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultDTO<UserVO> login(@RequestParam String username, @RequestParam String password,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入用户名和密码");
        }
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setUsernames(Sets.newHashSet(username));
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        if (CollectionUtils.isEmpty(userDTOs)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "用户不存在");
        }
        UserDTO userDTO = userDTOs.get(0);
        String passwordFromDB = userDTO.getPassword();
        String publickey = userDTO.getPublicKey();
        String encryPassword = AESUtil.encrypt(password, publickey);
        System.out.println(encryPassword.length());
       if (!encryPassword.equals(passwordFromDB)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "用户密码错误,请重新输入");
        }
        String userId = userDTO.getUserId();
        SessionUtils.addCookie(request, response, userId);
        return ResultDTO.successfy();
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO logout(HttpServletRequest request, HttpServletResponse response) {
        SessionUtils.deleteCookie(request, response);
        return ResultDTO.successfy();
    }

    /**
     * 用户注册
     * @param userRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> register(@RequestBody UserRequest userRequest) {
        return userAdapter.addUser(userRequest);
    }
}
