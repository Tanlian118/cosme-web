package com.cosme.request;

import com.cosme.param.BaseQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-12 22:42
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListParam extends BaseQueryParam {

    /**
     * 商品标题
     */
    String productTitle;

    /**
     * 频道id
     */
     Set<Integer> channelIds;
}
