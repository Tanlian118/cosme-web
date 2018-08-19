package com.cosme.backpratice.service;

import com.cosme.backpratice.dao.CarouselPraDAO;
import com.cosme.common.constant.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.CarouselDTO;
import com.cosme.entity.CarouselEntity;
import com.cosme.param.CarouselQueryParam;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 14:36
 **/
@Service("carouselPraService")
public class CarouselPraServiceImpl implements CarouselPraService {

   @Autowired
   private CarouselPraDAO carouselPraDAO;

    @Override
    public ResultDTO<Void> saveOrUpdateCarousel(CarouselDTO carouselDTO) {
        CarouselEntity carouselEntity = BaseTransformer.convert(carouselDTO, new CarouselEntity());
        if (carouselEntity.getCosmeImageId() == null) {
            carouselPraDAO.save(carouselEntity);
            return ResultDTO.successfy();
        }
        carouselPraDAO.update(carouselEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "轮播图id为空");
        }
        carouselPraDAO.delete(cosmeImageIds);
        return null;
    }

    @Override
    public PageModel<CarouselDTO> queryByParam(CarouselQueryParam queryParam) {
        if (queryParam == null) {
            return PageModel.emptyModel();
        }
        List<CarouselEntity> carouselEntities = carouselPraDAO.list(queryParam);
        List<CarouselDTO> carouselDTOs = Lists.newArrayList();
        for (CarouselEntity carouselEntity : carouselEntities) {
            CarouselDTO carouselDTO = BaseTransformer.convert(carouselEntity, new CarouselDTO());
            carouselDTOs.add(carouselDTO);
        }
        int count = carouselPraDAO.count(queryParam);
        return PageModel.build(carouselDTOs,count);
    }
}
