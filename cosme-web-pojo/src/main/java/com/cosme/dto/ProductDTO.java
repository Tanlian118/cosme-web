package com.cosme.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * @author Tanlian
 * @create 2018-08-11 18:56
 **/

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO implements Serializable {

    /**
     * 商品id
     */
    Integer productId;

    /**
     * 操作员Id
     */
    String operaterId;

    /**
     * 姓名
     */
    String username;

    /**
     * 商品主图
     */
    String mainImage;

    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 商品标题
     */
    String channelTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 商品频道
     */
    String productChannel;

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

}
