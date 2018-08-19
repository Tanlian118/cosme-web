package com.cosme.back.service;

import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.CarouselDTO;
import com.cosme.param.CarouselQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 19:55
 **/

public interface CarouselService {

    /**
     * 新增或修改轮播图信息
     * @param carouselDTO
     * @return
     */
    ResultDTO<Void> saveOrUpdateCarousel(CarouselDTO carouselDTO);

    /**
     * 刪除轮播图信息
     * @param cosmeImageId
     * @return
     */
    ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageId);

    /**
     * 查询轮播图信息
     * @param
     * @return
     */
    PageModel<CarouselDTO> queryByParam(CarouselQueryParam queryParam);
}

