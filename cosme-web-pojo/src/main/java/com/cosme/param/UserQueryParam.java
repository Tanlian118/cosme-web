package com.cosme.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQueryParam extends BaseQueryParam {

    /**
     * 用户id
     */
    Set<String> userIds;

    /**
     * 姓名
     */
    Set<String> usernames;

    /**
     * 密码
     */
    String password;

}
