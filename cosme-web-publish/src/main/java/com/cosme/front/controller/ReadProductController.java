package com.cosme.front.controller;

import com.cosme.front.adapter.ReadProductAdapter;
import com.cosme.request.ProductListParam;
import com.cosme.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-17 15:29
 **/
@RestController
@RequestMapping("/cm/pro")
public class ReadProductController {

    @Autowired
    private ReadProductAdapter readProductAdapter;

    /**
     * 读取护肤/彩妆列表
     * @param productListParam
     * @return
     */
    @RequestMapping(value = "readProduct", method = RequestMethod.GET)
    public List<ProductVO> readProduct(ProductListParam productListParam) {
        return readProductAdapter.readProduct(productListParam);
    }

}
