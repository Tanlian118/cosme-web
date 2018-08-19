package com.cosme.front.adapter;

import com.cosme.back.service.ProductService;
import com.cosme.back.transformers.ProductTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.ProductDTO;
import com.cosme.param.ProductQueryParam;
import com.cosme.request.ProductListParam;
import com.cosme.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-17 15:31
 **/
@Service("readProductAdapter")
public class ReadProductAdapter {

    @Autowired
    private ProductService productService;

    public List<ProductVO> readProduct(ProductListParam listParam) {
        if (listParam == null) {
            return Collections.emptyList();
        }
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setChannelIds(listParam.getChannelIds());

        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        if (pageSize == 10) {
            pageSize = 30;
        }
        productQueryParam.setPage(listParam.getPage() * pageSize);
        productQueryParam.setPageSize(pageSize);
        productQueryParam.setNeedPagination(true);
        List<ProductDTO> productDTOs = productService.queryByParam(productQueryParam).getData();
        return Lists2.transform(productDTOs, ProductTransformers.DTO_TO_VO);
    }
}
