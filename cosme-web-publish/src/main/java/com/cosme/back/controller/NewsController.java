package com.cosme.back.controller;

import com.cosme.back.adapter.NewsAdapter;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.request.NewsListParam;
import com.cosme.request.NewsAddRequest;
import com.cosme.vo.NewsVO;
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
 * @create 2018-08-12 10:21
 **/
@RestController
@RequestMapping("/bs/news")
public class NewsController extends BaseController{

    @Autowired
    private NewsAdapter newsAdapter;

    /**
     * 添加或修改新闻信息
     * @param newsRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody NewsAddRequest newsRequest, HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        newsRequest.setOperaterId(onlineUserId);
        return newsAdapter.addOrEditNews(newsRequest);
    }

    /**
     * 删除新闻信息
     * @param newsIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> newsIds) {
        return newsAdapter.deleteNews(newsIds);
    }

    /**
     * 查询新闻列表
     * @param newsListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<NewsVO> list(NewsListParam newsListParam) {
        return newsAdapter.listNews(newsListParam);
    }

}
