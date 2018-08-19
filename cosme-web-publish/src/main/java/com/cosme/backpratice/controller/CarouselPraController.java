package com.cosme.backpratice.controller;

import com.cosme.backpratice.adapter.CarouselPraAdapter;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.request.CarouselAddRequest;
import com.cosme.request.CarouselListParam;
import com.cosme.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 14:31
 **/
@RestController
@RequestMapping("cm/pra")
public class CarouselPraController {

    @Autowired
    private CarouselPraAdapter carouselPraAdapter;

    /**
     * 添加或修改轮播图信息
     * @param carouselAddRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> saveAndUpdate(@RequestBody CarouselAddRequest carouselAddRequest) {
        return carouselPraAdapter.saveAndUpdateCarousel(carouselAddRequest);
    }

    /**
     * 删除轮播表信息
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> deleteCarousel(@RequestBody Set<Integer> cosmeImageIds) {
        return carouselPraAdapter.deleteCarousel(cosmeImageIds);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<CarouselVO> list(CarouselListParam carouselListParam) {
        return carouselPraAdapter.listCarousel(carouselListParam);
    }
}
