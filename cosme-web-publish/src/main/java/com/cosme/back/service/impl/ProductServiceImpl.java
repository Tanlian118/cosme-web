package com.cosme.back.service.impl;

import com.cosme.back.dao.ProductDAO;
import com.cosme.back.transformers.ProductTransformers;
import com.cosme.api.ChannelService;
import com.cosme.api.ProductService;
import com.cosme.api.UserService;
import com.cosme.common.constant.StateCode;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.ChannelDTO;
import com.cosme.dto.ProductDTO;
import com.cosme.dto.UserDTO;
import com.cosme.entity.ProductEntity;
import com.cosme.param.ChannelQueryParam;
import com.cosme.param.ProductQueryParam;
import com.cosme.param.UserQueryParam;
import com.cosme.param.filling.ProductFillingParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-13 15:23
 **/
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productsDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;

    @Override
    public ResultDTO<Void> addOrEditProduct(ProductDTO productDTO) {
        ProductEntity productEntity = ProductTransformers.DTO_TO_ENTITY.apply(productDTO);

        if (productEntity.getProductId() == null) {
            productsDAO.save(productEntity);
            return ResultDTO.successfy();
        }
        productsDAO.update(productEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteProduct(Set<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "productIds is empty");
        }
        productsDAO.delete(productIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<ProductDTO> queryByParam(ProductQueryParam productQueryParam) {
        if (productQueryParam == null) {
            return PageModel.emptyModel();
        }
        List<ProductEntity> productEntities = productsDAO.queryByParam(productQueryParam);
        List<ProductDTO> productDTOs = Lists2.transform(productEntities, ProductTransformers.ENTITY_TO_DTO);
        int count = productsDAO.count(productQueryParam);
        ProductFillingParam fillingParam = productQueryParam.getFillingParam();
        fillProductDTODetails(productDTOs, fillingParam);
        return PageModel.build(productDTOs, count, productQueryParam.getPage(), productQueryParam.getPageSize());
    }

    private void fillProductDTODetails(List<ProductDTO> productDTOs, ProductFillingParam fillingParam) {
        if (CollectionUtils.isEmpty(productDTOs) || fillingParam == null) {
            return;
        }
        if (fillingParam.needUserDTO()) {
            UserQueryParam userQueryParam = fillingParam.getUserQueryParam();
            fillUserInfo(productDTOs, userQueryParam);
        }
        if (fillingParam.needChannelDTO()) {
            ChannelQueryParam channelQueryParam = fillingParam.getChannelQueryParam();
            fillChannelInfo(productDTOs, channelQueryParam);
        }
    }

    private void fillChannelInfo(List<ProductDTO> productDTOs, ChannelQueryParam channelQueryParam) {
        Set<Integer> channleIds = productDTOs.stream().map(ProductDTO::getChannelId).collect(Collectors.toSet());
        channelQueryParam.setChannelIds(channleIds);
        List<ChannelDTO> channelDTOs = channelService.queryByParam(channelQueryParam).getData();
//        Map<Integer, String> channelIdAndTitleMap = Maps.newHashMap();
//        for (ChannelDTO channelDTO : channelDTOs) {
//            channelIdAndTitleMap.put(channelDTO.getChannelId(), channelDTO.getChannelTitle());
//        }
        Map<Integer, String> channelIdAndTitleMap = channelDTOs.stream()
                .collect(Collectors.toMap(ChannelDTO::getChannelId, ChannelDTO::getChannelTitle));
        productDTOs.forEach(v -> v.setChannelTitle(channelIdAndTitleMap.get(v.getChannelId())));

    }

    private void fillUserInfo(List<ProductDTO> productDTOs, UserQueryParam userQueryParam) {
        Set<String> operaterIds = productDTOs.stream().map(ProductDTO::getOperaterId).collect(Collectors.toSet());
        userQueryParam.setUserIds(operaterIds);
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        Map<String, String> operaterIdAndNameMap = userDTOs.stream()
                .collect(Collectors.toMap(UserDTO::getUserId, UserDTO::getUsername));
        productDTOs.forEach(v -> v.setUsername(operaterIdAndNameMap.get(v.getOperaterId())));
    }
}
