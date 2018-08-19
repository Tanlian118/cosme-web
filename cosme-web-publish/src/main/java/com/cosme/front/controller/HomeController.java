package com.cosme.front.controller;

import com.cosme.front.adapter.HomeAdapter;
import com.cosme.request.NewsListParam;
import com.cosme.vo.CarouselVO;
import com.cosme.vo.ChannelVO;
import com.cosme.vo.NewsDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 17:07
 **/
@RestController
@RequestMapping("/cm/home")
public class HomeController {

    @Autowired
    private HomeAdapter homeAdapter;

    /**
     * 读取频道列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "readChannel", method = RequestMethod.GET)
    public List<ChannelVO> readChannel() {
        return homeAdapter.readChannel();
    }

    /**
     * 读取轮播图列表
     *
     * @return
     */
    @RequestMapping(value = "readCarousel", method = RequestMethod.GET)
    public List<CarouselVO> readCarousel() {
        return homeAdapter.readCarousel();
    }

    /**
     * 读取商品权重最大的商品
     */
    @RequestMapping(value = "searchProduct", method = RequestMethod.GET)
    public List<ChannelVO> searchProduct(@RequestParam Set<Integer> channelIds) {
        return homeAdapter.searchProduct(channelIds);
    }

    /**
     * 读取新闻列表
     * @return
     */
    @RequestMapping(value = "readNews", method = RequestMethod.GET)
    public List<NewsDetailsVO> readNews(NewsListParam newsListParam) {
        return homeAdapter.readNews(newsListParam);
    }

    /**
     * 读取新闻详情页
     * @return
     */
    @RequestMapping(value = "newsDetails", method = RequestMethod.GET)
    public List<NewsDetailsVO> readNewsDetails() {
        return homeAdapter.newsDetails();
    }
}
