package com.cosme.back.transformers;

import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.converter.SafeFunction;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.ChannelDTO;
import com.cosme.dto.ProductDTO;
import com.cosme.entity.ChannelEntity;
import com.cosme.vo.ChannelVO;
import com.cosme.vo.ProductVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: 谭宁
 * @datetime 2018-01-19 14:56
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelTransformers extends BaseTransformer {

    public static final SafeFunction<ChannelEntity, ChannelDTO> ENTITY_TO_DTO = input -> convert(input, new ChannelDTO());

    public static final SafeFunction<ChannelDTO, ChannelEntity> DTO_TO_ENTITY = input -> convert(input, new ChannelEntity());

    public static final SafeFunction<ChannelDTO, ChannelVO> DTO_TO_VO = input -> {
        ChannelVO channelVO = convert(input, new ChannelVO());
        List<ProductDTO> productDTOs = input.getProductDTOs();
        if (!CollectionUtils.isEmpty(productDTOs)) {
            List<ProductVO> productVOs = Lists2.transform(productDTOs, ProductTransformers.DTO_TO_VO);
            channelVO.setProductVOs(productVOs);
        }
        return channelVO;
    };

    public static final SafeFunction<ChannelDTO, ChannelVO> DTO_TO_SHORT_VO = input -> {
        ChannelVO channelVO = new ChannelVO();
        channelVO.setChannelTitle(input.getChannelTitle());
        channelVO.setJapaneseTitle(input.getJapaneseTitle());
        channelVO.setWeight(input.getWeight());
        return channelVO;
    };
}
