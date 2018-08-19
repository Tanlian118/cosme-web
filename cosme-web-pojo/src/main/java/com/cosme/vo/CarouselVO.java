package com.cosme.vo;

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
public class CarouselVO {

    /**
     * 轮播图id
     */
    Integer cosmeImageId;

    /**
     * 操作员id
     */
    Integer operaterId;

    /**
     * 轮播图
     */
    String carouselImage;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 操作员姓名
     */
    String userName;

    /**
     * 更新时间
     */
    Date updateTime;
}
