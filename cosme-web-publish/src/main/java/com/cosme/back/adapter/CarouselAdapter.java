package com.cosme.back.adapter;

import com.cosme.api.CarouselService;
import com.cosme.api.UserService;
import com.cosme.back.transformers.CarouselTransformers;
import com.cosme.common.constant.FixedPageSizeEnum;
import com.cosme.common.constant.StateCode;
import com.cosme.common.converter.BaseTransformer;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.common.guava2.Lists2;
import com.cosme.dto.CarouselDTO;
import com.cosme.dto.UserDTO;
import com.cosme.param.CarouselQueryParam;
import com.cosme.param.UserQueryParam;
import com.cosme.request.CarouselAddRequest;
import com.cosme.request.CarouselListParam;
import com.cosme.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-12 10:16
 **/
@Service("carouselAdapter")
public class CarouselAdapter {


    @Autowired
    private CarouselService carouselService;
    @Autowired
    private UserService userService;

    public ResultDTO<Void> addOrEditCarousel(CarouselAddRequest carouselRequest) {
        ResultDTO<Void> result = checkParam(carouselRequest);
        if (!result.isSuccess()) {
            return result;
        }
        CarouselDTO carouselDTO = BaseTransformer.convert(carouselRequest, new CarouselDTO());
        carouselService.saveOrUpdateCarousel(carouselDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(CarouselAddRequest carouselRequest) {
        if (carouselRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入轮播图信息");
        }
        if (!StringUtils.hasText(carouselRequest.getCarouselImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传轮播图");
        }
        if (carouselRequest.getWeight() < 0 || carouselRequest.getWeight() > 99) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "权重需控制在0-99之间");
        }
        if (carouselRequest.isNeedLinkUrl()) {
            if (!StringUtils.hasText(carouselRequest.getLinkUrl())) {
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入网页链接");
            }
        }
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的轮播图");
        }
        carouselService.deleteCarousel(cosmeImageIds);
        return ResultDTO.successfy();
    }

    public PageModel<CarouselVO> listCarousel(CarouselListParam carouselPram) {
        if (carouselPram == null) {
            return PageModel.emptyModel();
        }
        CarouselQueryParam carouselQueryParam = new CarouselQueryParam();
        int pageSize = FixedPageSizeEnum.getByPageSize(carouselPram.getPageSize()).getPageSize();
        carouselQueryParam.setPage(carouselPram.getPage() * pageSize);
        carouselQueryParam.setPageSize(pageSize);
        carouselQueryParam.setNeedPagination(true);
        PageModel<CarouselDTO> model = carouselService.queryByParam(carouselQueryParam);
        List<CarouselDTO> carouselDTOs = model.getData();
        List<CarouselVO> carouselVOs = Lists2.transform(carouselDTOs, CarouselTransformers.DTO_TO_VO);
        UserQueryParam userQueryParam = new UserQueryParam();
        Set<String> operaterIds = carouselDTOs.stream()
                .map(CarouselDTO::getOperaterId)
                .collect(Collectors.toSet());
        userQueryParam.setUserIds(operaterIds);
        List<UserDTO> userDTOs = userService.queryByParam(userQueryParam);
        Map<String, String> operaterIdAndNameMap = userDTOs.stream()
                .collect(Collectors.toMap(UserDTO::getUserId, UserDTO::getUsername));
        carouselVOs.stream().forEach(v->v.setUserName(operaterIdAndNameMap.get(v.getOperaterId())));
        return PageModel.build(carouselVOs, model.getTotalCount(), carouselPram.getPage(), pageSize);
    }
}
