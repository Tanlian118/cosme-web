package com.cosme.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * @author Tanlian
 * @create 2018-08-11 18:34
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseQueryParam<T> implements Serializable {

    /**
     * 分页索引，默认0
     */
    int page;
    /**
     * 分页大小
     */
    Integer pageSize;

    /**
     * 是否需要分页
     */
    boolean needPagination;

    /**
     * 填充信息
     */
    T FillingParam;

}
