package com.cosme.back.transformers;

import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.converter.SafeFunction;
import com.cosme.dto.UserDTO;
import com.cosme.entity.UserEntity;
import com.cosme.vo.UserVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-18 20:23
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersTransformers extends BaseTransformer {

    public static final SafeFunction<UserEntity, UserDTO> ENTITY_TO_DTO = input -> convert(input, new UserDTO());

    public static final SafeFunction<UserDTO, UserEntity> DTO_TO_ENTITY = input -> convert(input, new UserEntity());

    public static final SafeFunction<UserDTO, UserVO> DTO_TO_VO = input -> convert(input, new UserVO());


}
