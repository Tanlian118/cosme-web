package com.cosme.api;

import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.dto.ProductDTO;
import com.cosme.param.ProductQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-13 13:47
 **/
public interface ProductService {

    /**
     * 添加商品
     * @param productDTO
     * @return
     */
    ResultDTO<Void> addOrEditProduct(ProductDTO productDTO);

    /**
     * 删除商品信息
     * @param productIds
     * @return
     */
    ResultDTO<Void> deleteProduct(Set<Integer> productIds);

    /**
     * 查询商品信息
     * @param productQueryParam
     * @return
     */
    PageModel<ProductDTO> queryByParam(ProductQueryParam productQueryParam);


}
