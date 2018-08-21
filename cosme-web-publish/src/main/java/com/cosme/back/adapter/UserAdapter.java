package com.cosme.back.adapter;

import com.cosme.back.service.UserService;
import com.cosme.common.constant.StateCode;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.util.AESUtil;
import com.cosme.common.util.SessionUtils;
import com.cosme.dto.UserDTO;
import com.cosme.param.UserQueryParam;
import com.cosme.request.UserRequest;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * 用户模块
 *
 * @author Tanlian
 * @create 2018-08-19 15:28
 **/
@Service("userAdapter")
public class UserAdapter {

    @Autowired
    private UserService userService;

    public ResultDTO<Void> addUser(UserRequest userRequest) {
        if (userRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入对应信息");
        }
        if (!StringUtils.hasText(userRequest.getUsername())){
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入用户名");
        }
        if (!StringUtils.hasText(userRequest.getPassword())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入密码");
        }
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setUsernames(Sets.newHashSet(userRequest.getUsername()));
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        if (!CollectionUtils.isEmpty(userDTOs)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS,"用户名已存在，请重新输入");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setPublicKey(SessionUtils.PUBLIC_KEY);
        userDTO.setUsername(userRequest.getUsername());
        String password = userRequest.getPassword();
        String encryPassword = AESUtil.encrypt(password, SessionUtils.PUBLIC_KEY);
        userDTO.setPassword(encryPassword);
        String uuid = UUID.randomUUID().toString();
        userDTO.setUserId(uuid.replace("-",""));
        userService.saveUser(userDTO);
        return ResultDTO.successfy();
    }




}
