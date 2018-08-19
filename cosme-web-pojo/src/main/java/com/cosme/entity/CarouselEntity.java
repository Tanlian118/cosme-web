package com.cosme.entity;

import com.cosme.common.constant.StatusCode;
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
public class CarouselEntity {

    /**
     * 轮播图id
     */
    Integer cosmeImageId;

    /**
     * 操作员id
     */
    String operaterId;

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
