package com.cosme.back.controller;

import com.cosme.back.adapter.ChannelAdapter;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.request.ChannelAddRequest;
import com.cosme.request.ChannellListParam;
import com.cosme.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-12 10:22
 **/
@RestController
@RequestMapping("/bs/channel")
public class ChannelController extends BaseController{

    @Autowired
    private ChannelAdapter channelAdapter;


    /**
     * 添加或修改频道信息
     * @param channelRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody ChannelAddRequest channelRequest,
                                HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        channelRequest.setOperaterId(onlineUserId);
        return channelAdapter.addOrEditChannel(channelRequest);
    }

    /**
     * 删除频道信息
     * @param channelIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> channelIds) {
       return channelAdapter.deleteChannel(channelIds);
    }

    /**
     * 查询频道列表
     * @param channellListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<ChannelVO> list(ChannellListParam channellListParam) {
        return channelAdapter.listChannel(channellListParam);
    }
}
