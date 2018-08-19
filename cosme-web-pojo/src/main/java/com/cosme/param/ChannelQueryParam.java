package com.cosme.param;

import com.cosme.param.filling.ChannelFillingParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 18:43
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelQueryParam extends BaseQueryParam<ChannelFillingParam>{

    /**
     * 频道id
     */
    Set<Integer> channelIds;

    /**
     * 操作员id
     */
    Set<Integer> operaterIds;

    /**
     * 权重
     */
    Set<Integer> weights;
}
