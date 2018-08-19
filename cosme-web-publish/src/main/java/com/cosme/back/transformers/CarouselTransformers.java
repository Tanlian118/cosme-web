package com.cosme.back.transformers;

import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.converter.SafeFunction;
import com.cosme.dto.CarouselDTO;
import com.cosme.entity.CarouselEntity;
import com.cosme.vo.CarouselVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-18 20:23
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarouselTransformers extends BaseTransformer {

    public static final SafeFunction<CarouselEntity, CarouselDTO> ENTITY_TO_DTO = input -> convert(input, new CarouselDTO());

    public static final SafeFunction<CarouselDTO, CarouselEntity> DTO_TO_ENTITY = input -> convert(input, new CarouselEntity());

    public static final SafeFunction<CarouselDTO, CarouselVO> DTO_TO_VO = input -> convert(input, new CarouselVO());


}
