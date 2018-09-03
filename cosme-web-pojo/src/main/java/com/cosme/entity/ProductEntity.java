package com.cosme.entity;

import com.cosme.common.constant.StatusCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-11 18:56
 **/

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {

    /**
     * 商品id
     */
    Integer productId;

    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 操作员Id
     */
    Integer operaterId;

    /**
     * 商品主图
     */
    String mainImage;

    /**
     * 商品标题
     */
    String productTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 商品频道
     */
    String productChannel;

    /**
     * 发布时间
     */
    Date releaseTime;

    /**
     * 产品功效
     */
    String effect;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 条形码
     */
    String barCode;

    /**
     * 状态
     */
    StatusCode status;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 更新时间
     */
    Date updateTime;

}
