package com.cosme.back.controller;

import com.cosme.back.adapter.CarouselAdapter;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.request.CarouselAddRequest;
import com.cosme.request.CarouselListParam;
import com.cosme.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 19:07
 **/

@RestController
@RequestMapping("/bs/carousel")
public class CarouselController extends BaseController{

    @Autowired
    private CarouselAdapter carouselAdapter;

    /**
     * 添加或修改轮播图
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody CarouselAddRequest carouselRequest,
                                HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        carouselRequest.setOperaterId(onlineUserId);
        return carouselAdapter.addOrEditCarousel(carouselRequest);
    }

    /**
     * 删除轮播图
     * @param cosmeImageIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> cosmeImageIds) {
        return carouselAdapter.deleteCarousel(cosmeImageIds);
    }

    /**
     * 查询轮播图信息
     * @param carouselParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<CarouselVO> list(CarouselListParam carouselParam) {
     return carouselAdapter.listCarousel(carouselParam);
    }


}
