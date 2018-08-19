package com.cosme.dto;

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
public class CarouselDTO {

    /**
     * 轮播图id
     */
    Integer cosmeImageId;

    /**
     * 用户id
     */
    String operaterId;


    /**
     * 轮播图
     */
    String carouselImage;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 更新时间
     */
    Date updateTime;


}
