package com.cosme.backpratice.adapter;

import com.cosme.backpratice.service.CarouselPraService;
import com.cosme.common.FixedPageSizeEnum;
import com.cosme.common.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.CarouselDTO;
import com.cosme.param.CarouselQueryParam;
import com.cosme.request.CarouselAddRequest;
import com.cosme.request.CarouselListParam;
import com.cosme.vo.CarouselVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 14:33
 **/
@Service("carouselPraAdapter")
public class CarouselPraAdapter {

    @Autowired
    private CarouselPraService carouselPraService;

    public ResultDTO<Void> saveAndUpdateCarousel(CarouselAddRequest carouselRequest) {
        if (carouselRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(carouselRequest.getCarouselImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传轮播图");
        }
        if (carouselRequest.getWeight() < 0 || carouselRequest.getWeight() > 99) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "权重需控制在0-99之间");
        }
        if (carouselRequest.isNeedLinkUrl()) {
            if (!StringUtils.hasText(carouselRequest.getLinkUrl())) {
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入网页链接");
            }
        }
        CarouselDTO carouselDTO = BaseTransformer.convert(carouselRequest, new CarouselDTO());
        carouselPraService.saveOrUpdateCarousel(carouselDTO);
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除轮播图信息");
        }
        carouselPraService.deleteCarousel(cosmeImageIds);
        return ResultDTO.successfy();
    }

    public PageModel<CarouselVO> listCarousel(CarouselListParam carouselListParam) {
        int pageSize = FixedPageSizeEnum.getByPageSize(carouselListParam.getPageSize()).getPageSize();
        CarouselQueryParam carouselQueryParam = new CarouselQueryParam();
        carouselQueryParam.setPage(carouselListParam.getPage() * pageSize);
        carouselQueryParam.setNeedPagination(true);
        carouselQueryParam.setPageSize(pageSize);
        PageModel<CarouselDTO> carouselDTOPageModel = carouselPraService.queryByParam(carouselQueryParam);
        List<CarouselDTO> carouselDTOs = carouselDTOPageModel.getData();
        List<CarouselVO> carouselVOs = Lists.newArrayList();
        for (CarouselDTO carouselDTO : carouselDTOs) {
            CarouselVO carouselVO = BaseTransformer.convert(carouselDTO, new CarouselVO());
            carouselVOs.add(carouselVO);
        }
        return PageModel.build(carouselVOs, carouselDTOPageModel.getTotalCount(), carouselListParam.getPage(),pageSize);
    }
}
