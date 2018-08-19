package com.cosme.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarouselAddRequest {

    /**
     * 轮播图id
     */
    Integer cosmeImageId;

    /**
     * 当前用户id
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
     * 是否需要关联内容
     */
    boolean needLinkUrl;
}
