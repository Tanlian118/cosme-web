package com.cosme.param.filling;

import com.cosme.param.ChannelQueryParam;
import com.cosme.param.UserQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-18 16:26
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFillingParam {

    /**
     * 填充用户信息
     */
    UserQueryParam userQueryParam;

    /**
     * 填充频道信息
     */
    ChannelQueryParam channelQueryParam;

    public boolean needUserDTO() {
        return userQueryParam != null;
    }

    public boolean needChannelDTO() {
        return channelQueryParam != null;
    }
}
