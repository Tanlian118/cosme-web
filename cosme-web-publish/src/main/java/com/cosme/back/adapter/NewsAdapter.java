package com.cosme.back.adapter;

import com.cosme.back.service.NewsService;
import com.cosme.back.service.UserService;
import com.cosme.back.transformers.NewsTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.constant.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.NewsDTO;
import com.cosme.dto.UserDTO;
import com.cosme.param.NewsQueryParam;
import com.cosme.param.UserQueryParam;
import com.cosme.request.NewsAddRequest;
import com.cosme.request.NewsListParam;
import com.cosme.vo.NewsVO;
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
 * @create 2018-08-13 13:45
 **/
@Service("newsAdapter")
public class NewsAdapter {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    public ResultDTO<Void> addOrEditNews(NewsAddRequest newsRequest) {
        ResultDTO<Void> result= checkParam(newsRequest);
        if (!result.isSuccess()) {
            return result;
        }
        NewsDTO newsDTO = BaseTransformer.convert(newsRequest, new NewsDTO());
        newsService.addOrUpdateNews(newsDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(NewsAddRequest newsRequest) {
        if (newsRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(newsRequest.getNewsImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传新闻图片");
        }
        if (!StringUtils.hasText(newsRequest.getNewsTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入新闻标题");
        }
        if (newsRequest.getNewsTitle().length() > 30) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "标题需控制在30个字之间!");
        }
        if (!StringUtils.hasText(newsRequest.getContent())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入内容");
        }
        if (newsRequest.getContent().length() > 2000) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "内容字数需控制在2000个字内！");
        }
        if (!StringUtils.hasText(newsRequest.getContentSummary())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入内容简介");
        }
        if (newsRequest.getContentSummary().length() > 100) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "内容简介需控制在100个字内！");
        }
        if (newsRequest.getReleaseTime() == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择发布时间");
        }
        return null;
    }

    public ResultDTO<Void> deleteNews(Set<Integer> newsIds) {
        if (CollectionUtils.isEmpty(newsIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选组要删除的信息");
        }
        newsService.deleteNews(newsIds);
        return ResultDTO.successfy();
    }

    public PageModel<NewsVO> listNews(NewsListParam newsListParam) {
        if (newsListParam == null) {
            return PageModel.emptyModel();
        }
        int pageSize = FixedPageSizeEnum.getByPageSize(newsListParam.getPageSize()).getPageSize();
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        newsQueryParam.setNeedPagination(true);
        newsQueryParam.setPage(newsListParam.getPage()*pageSize);
        newsQueryParam.setPageSize(pageSize);
        PageModel<NewsDTO> pageModel = newsService.queryByParam(newsQueryParam);
        List<NewsDTO> newsDTOs = pageModel.getData();
        List<NewsVO> newsVOs = Lists2.transform(newsDTOs, NewsTransformers.DTO_TO_VO);
        UserQueryParam userQueryParam = new UserQueryParam();
        Set<String> operaterIds = newsDTOs.stream()
                .map(v -> v.getOperaterId())
                .collect(Collectors.toSet());
        userQueryParam.setUserIds(operaterIds);
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        Map<String, String> operaterIdAndNameMap = userDTOs.stream()
                .collect(Collectors.toMap(UserDTO::getUserId, UserDTO::getUsername));
        newsVOs.forEach(v->v.setUserName(operaterIdAndNameMap.get(v.getOperaterId())));
        return PageModel.build(newsVOs, pageModel.getTotalCount(), newsListParam.getPage(), pageSize);
    }
}
