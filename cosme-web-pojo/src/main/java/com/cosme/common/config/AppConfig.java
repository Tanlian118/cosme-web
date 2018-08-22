package com.cosme.common.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-21 22:02
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
    /**
     * 允许登录失败次数
     */
    Integer loginFailures;
    /**
     * 账号封禁时间,单位:秒
     */
    Integer bannedSeconds;

}
