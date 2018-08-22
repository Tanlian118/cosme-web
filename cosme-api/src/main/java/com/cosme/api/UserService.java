package com.cosme.api;

import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.UserDTO;
import com.cosme.param.UserQueryParam;

import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-15 14:00
 **/
public interface UserService {


    /**
     * 查询用户信息
     * @param userQueryParam
     * @return
     */
    List<UserDTO> queryByParam(UserQueryParam userQueryParam);

    /**
     * 添加用户信息
     * @param userDTO
     * @return
     */
    ResultDTO<Void> saveUser(UserDTO userDTO);

}
