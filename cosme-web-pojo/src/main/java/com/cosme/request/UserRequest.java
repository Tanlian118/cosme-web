package com.cosme.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {


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
}
