package com.cosme.back.adapter;

import com.cosme.back.service.ProductService;
import com.cosme.back.transformers.ProductTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.constant.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.ProductDTO;
import com.cosme.param.ChannelQueryParam;
import com.cosme.param.ProductQueryParam;
import com.cosme.param.UserQueryParam;
import com.cosme.param.filling.ProductFillingParam;
import com.cosme.request.ProductAddRequest;
import com.cosme.request.ProductListParam;
import com.cosme.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-13 13:46
 **/
@Service("productAdapter")
public class ProductAdapter {

    @Autowired
    private ProductService productService;

    public ResultDTO<Void> addOrEditProduct(ProductAddRequest productRequest) {
        ResultDTO<Void> result = checkParam(productRequest);
        if (!result.isSuccess()) {
            return result;
        }
        ProductDTO productDTO = BaseTransformer.convert(productRequest, new ProductDTO());
        productService.addOrEditProduct(productDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(ProductAddRequest productRequest) {
        if (productRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(productRequest.getMainImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传商品图片");
        }
        if (!StringUtils.hasText(productRequest.getProductTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入商品标题");
        }
        if (productRequest.getProductTitle().length() > 13) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "商品标题需在13个字以内");
        }
        if (!StringUtils.hasText(productRequest.getJapaneseTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入日文标题");
        }
        if (!StringUtils.hasText(productRequest.getProductChannel())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择商品频道");
        }
        if (productRequest.getJapaneseTitle().length() > 20) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "日文标题需在20个字以内");
        }
        Integer channelId = productRequest.getChannelId();
        if (channelId == null || channelId <= 0) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择商品频道");
        }
        if (!StringUtils.hasText(productRequest.getEffect())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入产品功效");
        }
        if (productRequest.getEffect().length() > 13) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "产品功效需在13个字以内");
        }
        if (!StringUtils.hasText(productRequest.getBarCode())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入条形码");
        }
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteProduct(Set<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的商品");
        }
        productService.deleteProduct(productIds);
        return ResultDTO.successfy();
    }

    public PageModel<ProductVO> listProduct(ProductListParam productListParam) {
        if (productListParam == null) {
            return PageModel.emptyModel();
        }
        ProductQueryParam productQueryParam = getProductQueryParam(productListParam);
        PageModel<ProductDTO> pageModel = productService.queryByParam(productQueryParam);
        List<ProductDTO> productDTOs = pageModel.getData();
        List<ProductVO> productVOs = Lists2.transform(productDTOs, ProductTransformers.DTO_TO_VO);
        return PageModel.build(productVOs, pageModel.getTotalCount(), pageModel.getPage(), pageModel.getPageSize());
    }

    private ProductQueryParam getProductQueryParam(ProductListParam productListParam) {
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(productListParam.getPageSize()).getPageSize();
        productQueryParam.setPage(productListParam.getPage() * pageSize);
        productQueryParam.setPageSize(pageSize);
        productQueryParam.setChannelIds(productListParam.getChannelIds());
        productQueryParam.setProductTitle(productListParam.getProductTitle());

        ProductFillingParam fillingParam = new ProductFillingParam();
        fillingParam.setUserQueryParam(new UserQueryParam());
        fillingParam.setChannelQueryParam(new ChannelQueryParam());
        productQueryParam.setFillingParam(fillingParam);
        return productQueryParam;
    }
}
