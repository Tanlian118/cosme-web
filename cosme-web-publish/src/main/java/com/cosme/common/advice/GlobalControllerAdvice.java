package com.cosme.common.advice;

import com.cosme.common.dto.ResultDTO;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * Controller全局拦截器
 *
 * @Author: 黄志泉
 * @Datetime 2017-06-01 10:24
 */
//@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 除了返回值为ResultDTO之外的Controller方法都将被拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getMethod().getReturnType().isAssignableFrom(ResultDTO.class);
    }

    /**
     * 用ResultDTO包装返回值, 除非本身已经是ResultDTO
     */
    @Override
    public ResultDTO beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return ResultDTO.successfy(body);
    }

    /**
     * 避免向客户端暴露具体异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        System.out.println("=====GlobalControllerAdvice==== Exception:" + e);
    }
}
