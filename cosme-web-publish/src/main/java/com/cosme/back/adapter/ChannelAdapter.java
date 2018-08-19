package com.cosme.back.adapter;

import com.cosme.back.service.ChannelService;
import com.cosme.back.service.UserService;
import com.cosme.back.transformers.ChannelTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.constant.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.ChannelDTO;
import com.cosme.dto.UserDTO;
import com.cosme.param.ChannelQueryParam;
import com.cosme.param.UserQueryParam;
import com.cosme.request.ChannelAddRequest;
import com.cosme.request.ChannellListParam;
import com.cosme.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-12 20:54
 **/
@Service("channelAdapter")
public class ChannelAdapter {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;

    public ResultDTO<Void> addOrEditChannel(ChannelAddRequest channelRequest) {
        ResultDTO<Void> result = checkChannelRequestParam(channelRequest);
        if (!result.isSuccess()) {
            return result;
        }
        ChannelDTO channelDTO = BaseTransformer.convert(channelRequest, new ChannelDTO());
        channelService.addOrUpdateChannel(channelDTO);
        return ResultDTO.successfy();
    }
    private ResultDTO<Void> checkChannelRequestParam(ChannelAddRequest channelRequest) {
        if (channelRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(channelRequest.getMainImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传频道主图");
        }
        if (!StringUtils.hasText(channelRequest.getChannelTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入频道标题");
        }
        if (channelRequest.getChannelTitle().length() > 5) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "频道标题需控制在5个字之间！");
        }
        if (!StringUtils.hasText(channelRequest.getJapaneseTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入日文标题");
        }
        if (channelRequest.getJapaneseTitle().length() > 10) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "标题需控制在10个字之间！");
        }
        if (channelRequest.getWeight() < 0 || channelRequest.getWeight() > 99) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "权重需控制在0-99之间！");
        }

        if (channelRequest.isNeedLinkUrl()) {
            if (channelRequest.getWeight() == null) {
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入网页链接");
            }
        }
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteChannel(Set<Integer> channelIds) {
        if (CollectionUtils.isEmpty(channelIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的信息");
        }
        channelService.deleteChannel(channelIds);
        return ResultDTO.successfy();
    }

    public PageModel<ChannelVO> listChannel(ChannellListParam channellListParam) {
        if (channellListParam == null) {
            return PageModel.emptyModel();
        }
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        int pageSize = FixedPageSizeEnum.getByPageSize(channellListParam.getPageSize()).getPageSize();
        channelQueryParam.setNeedPagination(true);
        channelQueryParam.setPageSize(pageSize);
        channelQueryParam.setPage(channellListParam.getPage() * pageSize);
        PageModel<ChannelDTO> pageModel = channelService.queryByParam(channelQueryParam);
        List<ChannelDTO> channelDTOs = pageModel.getData();
        List<ChannelVO> channelVOs = Lists2.transform(channelDTOs, ChannelTransformers.DTO_TO_VO);
        UserQueryParam userQueryParam = new UserQueryParam();
        Set<String> operaterIds = channelDTOs.stream()
                .map(ChannelDTO::getOperaterId)
                .collect(Collectors.toSet());
        userQueryParam.setUserIds(operaterIds);
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        Map<String, String> operaterIdAndNameMap = userDTOs.stream()
                .collect(Collectors.toMap(UserDTO::getUserId, UserDTO::getUsername));
        channelVOs.stream()
                .forEach(v -> v.setUserName(operaterIdAndNameMap.get(v.getOperaterId())));
        return PageModel.build(channelVOs, pageModel.getTotalCount(), channellListParam.getPage(), pageSize);
    }
}
