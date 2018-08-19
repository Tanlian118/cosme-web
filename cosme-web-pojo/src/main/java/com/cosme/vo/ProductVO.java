package com.cosme.vo;

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
public class ProductVO {

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
    String channelTitle;

    /**
     * 日文标题
     */
    String JapaneseTitle;

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
     * 操作员姓名
     */
    String userName;

    /**
     * 更新时间
     */
    Date updateTime;

}
