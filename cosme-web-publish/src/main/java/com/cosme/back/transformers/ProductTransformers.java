package com.cosme.back.transformers;

import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.converter.SafeFunction;
import com.cosme.dto.ProductDTO;
import com.cosme.entity.ProductEntity;
import com.cosme.vo.ProductVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-18 21:14
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductTransformers extends BaseTransformer {

    public static final SafeFunction<ProductEntity, ProductDTO> ENTITY_TO_DTO = input -> convert(input, new ProductDTO());

    public static final SafeFunction<ProductDTO,ProductEntity> DTO_TO_ENTITY = input -> convert(input, new ProductEntity());

    public static final SafeFunction<ProductDTO, ProductVO> DTO_TO_VO = input -> convert(input, new ProductVO());

}
