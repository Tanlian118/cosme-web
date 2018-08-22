package com.cosme.back.service.impl;

import com.cosme.back.dao.UserDAO;
import com.cosme.back.transformers.UsersTransformers;
import com.cosme.api.UserService;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.UserDTO;
import com.cosme.entity.UserEntity;
import com.cosme.param.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-15 14:04
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserDTO> queryByParam(UserQueryParam userQueryParam) {
        List<UserEntity> userEntities = userDAO.queryByParam(userQueryParam);
        List<UserDTO> userDTOs = Lists2.transform(userEntities, UsersTransformers.ENTITY_TO_DTO);
        return userDTOs;
    }

    @Override
    public ResultDTO<Void> saveUser(UserDTO userDTO) {
        UserEntity userEntity = BaseTransformer.convert(userDTO, new UserEntity());
        userDAO.save(userEntity);
        return ResultDTO.successfy();
    }
}
