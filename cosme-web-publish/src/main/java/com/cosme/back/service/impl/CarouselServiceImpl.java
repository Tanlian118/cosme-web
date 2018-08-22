package com.cosme.back.service.impl;

import com.cosme.back.dao.CarouselDAO;
import com.cosme.back.service.CarouselService;
import com.cosme.back.transformers.CarouselTransformers;
import com.cosme.common.StateCode;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.CarouselDTO;
import com.cosme.entity.CarouselEntity;
import com.cosme.param.CarouselQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-11 20:20
 **/
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselDAO carouselDAO;

    @Override
    public ResultDTO<Void> saveOrUpdateCarousel(CarouselDTO carouselDTO) {
        CarouselEntity carouselEntity = CarouselTransformers.DTO_TO_ENTITY.apply(carouselDTO);
        if (carouselDTO.getCosmeImageId() == null) {
            int save = carouselDAO.save(carouselEntity);
            System.out.println(save);
            return ResultDTO.successfy();
        }
        carouselDAO.update(carouselEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "cosmeImageIds is empty");
        }
        carouselDAO.delete(cosmeImageIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<CarouselDTO> queryByParam(CarouselQueryParam queryParam) {
        if (queryParam == null) {
            return PageModel.emptyModel();
        }
        int count = carouselDAO.count(queryParam);

        List<CarouselEntity> carouselEntities = carouselDAO.queryByParam(queryParam);
        List<CarouselDTO> carouselDTOs = Lists2.transform(carouselEntities, CarouselTransformers.ENTITY_TO_DTO);
        return PageModel.build(carouselDTOs,count) ;
    }

}
