package com.cosme.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-11 18:56
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAddRequest {


    /**
     * 操作员id
     */
    String operaterId;

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
     * 频道id
     */
    Integer channelId;

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
