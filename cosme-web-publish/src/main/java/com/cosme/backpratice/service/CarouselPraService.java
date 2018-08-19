package com.cosme.backpratice.service;

import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.CarouselDTO;
import com.cosme.param.CarouselQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 14:35
 **/
public interface CarouselPraService {

    /**
     * 添加或修改轮播图信息
     * @param carouselDTO
     */
    ResultDTO<Void> saveOrUpdateCarousel(CarouselDTO carouselDTO);


    /**
     * 删除轮播图信息
     * @param cosmeImageIds
     * @return
     */
    ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds);

    /**
     * 查询轮播表信息
     * @param queryParam
     * @return
     */
    PageModel<CarouselDTO> queryByParam(CarouselQueryParam queryParam);
}
