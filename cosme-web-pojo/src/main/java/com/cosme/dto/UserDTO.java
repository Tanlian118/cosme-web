package com.cosme.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO implements Serializable {

    /**
     * 用户id
     */
    String userId;

    /**
     * 姓名
     */
    String username;

    /**
     * 密码
     */
    String password;

    /**
     * 性别
     */
    Integer gender;

    /**
     * 联系方式
     */
    String contact;

    /**
     * 生日日期
     */
    Date birthday;

    /**
     * 加密秘钥
     */
    String publicKey;

    /**
     * 更新时间
     */
    Date updateTime;
}
