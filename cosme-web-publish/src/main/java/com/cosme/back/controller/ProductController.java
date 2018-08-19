package com.cosme.back.controller;

import com.cosme.back.adapter.ProductAdapter;
import com.cosme.common.dto.PageModel;
import com.cosme.common.dto.ResultDTO;
import com.cosme.request.ProductAddRequest;
import com.cosme.request.ProductListParam;
import com.cosme.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-12 10:20
 **/
@RestController
@RequestMapping("bs/product")
public class ProductController extends BaseController{

    @Autowired
    private ProductAdapter productAdapter;


    /**
     * 添加或修改商品
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody ProductAddRequest productRequest, HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        productRequest.setOperaterId(onlineUserId);
        return productAdapter.addOrEditProduct(productRequest);
    }

    /**
     * 删除商品信息
     * @param productIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> productIds) {
        return productAdapter.deleteProduct(productIds);
    }

    /**
     * 查询商品信息
     * @param productListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<ProductVO> list(ProductListParam productListParam) {
        return productAdapter.listProduct(productListParam);
    }
}
