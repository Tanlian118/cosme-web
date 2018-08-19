package com.cosme.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-11 18:43
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelDTO {

    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 用户id
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
     * 更新时间
     */
    Date updateTime;

    /**
     * 商品信息
     */
    List<ProductDTO> productDTOs;
}
