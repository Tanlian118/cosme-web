package com.cosme.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-11 18:43
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelAddRequest {

    /**
     * 操作员id
     */
    String operaterId;

    /**
     * 频道标题
     */
    String channelTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 频道主图
     */
    String mainImage;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 是否需要关联内容
     */
    boolean needLinkUrl;
}
