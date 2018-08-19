package com.cosme.back.service;

import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.NewsDTO;
import com.cosme.param.NewsQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-13 13:46
 **/
public interface NewsService {

    /**
     * 添加或修改新闻
     * @param newsDTO
     */
    ResultDTO<Void> addOrUpdateNews(NewsDTO newsDTO);

    /**
     * 删除新闻信息
     * @param newsIds
     * @return
     */
    ResultDTO<Void> deleteNews(Set<Integer> newsIds);

    /**
     * 查询新闻信息
     * @param newsQueryParam
     * @return
     */
    PageModel<NewsDTO> queryByParam(NewsQueryParam newsQueryParam);
}
