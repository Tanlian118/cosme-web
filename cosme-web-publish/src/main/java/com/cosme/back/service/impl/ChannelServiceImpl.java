package com.cosme.back.service.impl;

import com.cosme.back.dao.ChannelDAO;
import com.cosme.back.service.ChannelService;
import com.cosme.back.service.ProductService;
import com.cosme.back.transformers.ChannelTransformers;
import com.cosme.common.constant.StateCode;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.common.guava2.Maps2;
import com.cosme.dto.ChannelDTO;
import com.cosme.dto.ProductDTO;
import com.cosme.entity.ChannelEntity;
import com.cosme.param.ChannelQueryParam;
import com.cosme.param.ProductQueryParam;
import com.cosme.param.filling.ChannelFillingParam;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-12 21:30
 **/
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private ProductService productService;


    @Override
    public ResultDTO<Void> addOrUpdateChannel(ChannelDTO channelDTO) {
        ChannelEntity channelEntity = ChannelTransformers.DTO_TO_ENTITY.apply(channelDTO);
        if (channelDTO.getChannelId() == null) {
            channelDAO.save(channelEntity);
            return ResultDTO.successfy();
        }
        channelDAO.update(channelEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteChannel(Set<Integer> channelIds) {
        if (CollectionUtils.isEmpty(channelIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "channelIds is empty");
        }
        channelDAO.delete(channelIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<ChannelDTO> queryByParam(ChannelQueryParam channelQueryParam) {
        if (channelQueryParam == null) {
            return PageModel.emptyModel();
        }
        List<ChannelEntity> channelEntities = channelDAO.queryByParam(channelQueryParam);
        List<ChannelDTO> channelDTOs = Lists2.transform(channelEntities, ChannelTransformers.ENTITY_TO_DTO);

        ChannelFillingParam fillingParam = channelQueryParam.getFillingParam();
        fillingChannelDetails(channelDTOs, fillingParam);
        int count = channelDAO.count(channelQueryParam);
        return PageModel.build(channelDTOs, count);
    }

    private void fillingChannelDetails(List<ChannelDTO> channelDTOs, ChannelFillingParam fillingParam) {
        if (CollectionUtils.isEmpty(channelDTOs) || fillingParam == null) {
            return;
        }
        if (fillingParam.needProductDTO()) {
            ProductQueryParam productQueryParam = fillingParam.getProductQueryParam();
            fillingProductInfo(channelDTOs, productQueryParam);
        }
    }

    private void fillingProductInfo(List<ChannelDTO> channelDTOs, ProductQueryParam queryParam) {
        Set<Integer> channelIds = channelDTOs.stream()
                .map(ChannelDTO::getChannelId)
                .collect(Collectors.toSet());
        queryParam.setChannelIds(channelIds);
        List<ProductDTO> productDTOs = productService.queryByParam(queryParam).getData();
        if (CollectionUtils.isEmpty(productDTOs)) {
            return;
        }
//        Map<Integer, List<ProductDTO>> channelIdAndProductDTOsMap = Maps.newHashMap();
//
//        for (ProductDTO productDTO : productDTOs) {
//            Integer channelId = productDTO.getChannelId();
//            if (channelIdAndProductDTOsMap.containsKey(channelId)) {
//                List<ProductDTO> exitProductDTOs = channelIdAndProductDTOsMap.get(channelId);
//                exitProductDTOs.add(productDTO);
//            } else {
//                List<ProductDTO> fillproductDTOs = Lists.newArrayList();
//                fillproductDTOs.add(productDTO);
//                channelIdAndProductDTOsMap.put(channelId, fillproductDTOs);
////                channelIdAndProductDTOsMap.put(channelId, Lists.newArrayList(productDTO));
//            }
//        }
        ArrayListMultimap<Integer, ProductDTO> channelIdAndProductDTOsMap = Maps2.newMultimapWithValue(productDTOs, ProductDTO::getChannelId);
//        Map<Integer, ProductDTO> integerProductDTOMap = Maps2.newMapWithValue(productDTOs, v -> v.getChannelId());
//        ProductDTO productDTO = integerProductDTOMap.get(1);
        channelDTOs.forEach(v->v.setProductDTOs(channelIdAndProductDTOsMap.get(v.getChannelId())));
    }
}
