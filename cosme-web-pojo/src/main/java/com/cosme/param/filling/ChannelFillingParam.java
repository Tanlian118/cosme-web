package com.cosme.param.filling;

import com.cosme.param.ProductQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-18 17:22
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelFillingParam {

    /**
     * 填充商品信息
     */
    ProductQueryParam productQueryParam;

    public boolean needProductDTO() {
        return productQueryParam != null;
    }

}
