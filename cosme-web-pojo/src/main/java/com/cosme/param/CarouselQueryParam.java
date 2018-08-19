package com.cosme.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarouselQueryParam extends BaseQueryParam {

    /**
     * 轮播图id
     */
    Set<Integer> cosmeImageIds;

    /**
     * 操作员id
     */
    Set<Integer> operaterIds;

    /**
     * 权重
     */
    Set<Integer> weights;
}
