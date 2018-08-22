package com.cosme.front.adapter;

import com.cosme.api.CarouselService;
import com.cosme.api.ChannelService;
import com.cosme.api.NewsService;
import com.cosme.api.ProductService;
import com.cosme.back.transformers.CarouselTransformers;
import com.cosme.back.transformers.ChannelTransformers;
import com.cosme.back.transformers.NewsTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.CarouselDTO;
import com.cosme.dto.ChannelDTO;
import com.cosme.dto.NewsDTO;
import com.cosme.param.CarouselQueryParam;
import com.cosme.param.ChannelQueryParam;
import com.cosme.param.NewsQueryParam;
import com.cosme.param.ProductQueryParam;
import com.cosme.param.filling.ChannelFillingParam;
import com.cosme.request.NewsListParam;
import com.cosme.vo.CarouselVO;
import com.cosme.vo.ChannelVO;
import com.cosme.vo.NewsDetailsVO;
import com.cosme.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 22:43
 **/
@Service("homeAdapter")
public class HomeAdapter {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private NewsService newsService;

    public List<ChannelVO> readChannel() {
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        List<ChannelDTO> channelDTOs = channelService.queryByParam(channelQueryParam).getData();
        if (CollectionUtils.isEmpty(channelDTOs)) {
            return Collections.emptyList();
        }
        return Lists2.transform(channelDTOs, ChannelTransformers.DTO_TO_SHORT_VO);
    }

    public List<CarouselVO> readCarousel() {
        CarouselQueryParam carouselQueryParam = new CarouselQueryParam();
        List<CarouselDTO> carouselDTOs = carouselService.queryByParam(carouselQueryParam).getData();
        List<CarouselVO> carouselVOs = Lists2.transform(carouselDTOs, CarouselTransformers.DTO_TO_VO);
        return carouselVOs.subList(0,5);
    }

    public List<ChannelVO> searchProduct(Set<Integer> channelIds) {
        ChannelQueryParam channelQueryParam = getChannelQueryParam(channelIds);
        List<ChannelDTO> channelDTOs = channelService.queryByParam(channelQueryParam).getData();
        List<ChannelVO> channelVOs = Lists2.transform(channelDTOs, ChannelTransformers.DTO_TO_VO);
        for (ChannelVO channelVO : channelVOs) {
            List<ProductVO> channelVOProductVOs = channelVO.getProductVOs();
            if (!CollectionUtils.isEmpty(channelVOProductVOs)) {
                if (channelVOProductVOs.size() > 2) {
                    List<ProductVO> productVOList = channelVOProductVOs.subList(0, 2);
                    channelVO.setProductVOs(productVOList);
                }
            }
        }
        return channelVOs;
    }

    private ChannelQueryParam getChannelQueryParam(Set<Integer> channelIds) {
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        channelQueryParam.setChannelIds(channelIds);
        ChannelFillingParam channelFillingParam = new ChannelFillingParam();
        channelFillingParam.setProductQueryParam(new ProductQueryParam());
        channelQueryParam.setFillingParam(channelFillingParam);
        return channelQueryParam;
    }

    public List<NewsDetailsVO> readNews(NewsListParam newsListParam) {
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        newsQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(newsListParam.getPage()).getPageSize();
        newsQueryParam.setPage(newsListParam.getPage() * pageSize);
        newsQueryParam.setPageSize(pageSize);
        List<NewsDTO> NewsDetailsVOs = newsService.queryByParam(newsQueryParam).getData();
        return Lists2.transform(NewsDetailsVOs, NewsTransformers.DTO_TO_DETAILS_VO);
    }

    public List<NewsDetailsVO> newsDetails() {
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        List<NewsDTO> newsDTOs = newsService.queryByParam(newsQueryParam).getData();
        return Lists2.transform(newsDTOs, NewsTransformers.DTO_TO_DETAILS_VO);
    }
}
