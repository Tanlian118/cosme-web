package com.cosme.back.transformers;

import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.converter.SafeFunction;
import com.cosme.dto.NewsDTO;
import com.cosme.entity.NewsEntity;
import com.cosme.vo.NewsDetailsVO;
import com.cosme.vo.NewsVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-18 21:11
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsTransformers extends BaseTransformer {

    public static final SafeFunction<NewsEntity,NewsDTO> ENTITY_TO_DTO = input -> convert(input, new NewsDTO());

    public static final SafeFunction<NewsDTO, NewsEntity> DTO_TO_ENTITY = input -> convert(input, new NewsEntity());

    public static final SafeFunction<NewsDTO, NewsVO> DTO_TO_VO = input -> convert(input, new NewsVO());

    public static final SafeFunction<NewsDTO, NewsDetailsVO> DTO_TO_DETAILS_VO = input -> convert(input, new NewsDetailsVO());


}
