package com.cosme.back.service.impl;

import com.cosme.back.dao.NewsDAO;
import com.cosme.back.transformers.NewsTransformers;
import com.cosme.api.NewsService;
import com.cosme.common.constant.StateCode;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.NewsDTO;
import com.cosme.entity.NewsEntity;
import com.cosme.param.NewsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-13 14:12
 **/
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public ResultDTO<Void> addOrUpdateNews(NewsDTO newsDTO) {
        NewsEntity newsEntity = NewsTransformers.DTO_TO_ENTITY.apply(newsDTO);
        if (newsDTO.getNewsId() == null) {
            newsDAO.save(newsEntity);
            return ResultDTO.successfy();
        }
        newsDAO.update(newsEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteNews(Set<Integer> newsIds) {
        if (CollectionUtils.isEmpty(newsIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "newsIds is empty");
        }
        newsDAO.delete(newsIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<NewsDTO> queryByParam(NewsQueryParam newsQueryParam) {
        if (newsQueryParam == null) {
            return PageModel.emptyModel();
        }
        List<NewsEntity> newsEntities = newsDAO.queryByParam(newsQueryParam);
        List<NewsDTO> newsDTOs = Lists2.transform(newsEntities, NewsTransformers.ENTITY_TO_DTO);
        int count = newsDAO.count(newsQueryParam);
        return PageModel.build(newsDTOs, count);
    }
}
