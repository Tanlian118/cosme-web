package com.cosme.api;

import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.ChannelDTO;
import com.cosme.param.ChannelQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-12 20:57
 **/
public interface ChannelService {

    /**
     * 添加频道信息
     * @param channelDTO
     * @return
     */
    ResultDTO<Void> addOrUpdateChannel(ChannelDTO channelDTO);

    /**
     * 删除频道信息
     * @param channelIds
     */
    ResultDTO<Void> deleteChannel(Set<Integer> channelIds);


    /**
     * 查询频道信息
     * @param channelQueryParam
     * @return
     */
    PageModel<ChannelDTO> queryByParam(ChannelQueryParam channelQueryParam);

}
