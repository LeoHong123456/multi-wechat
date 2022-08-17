package com.app.wechat.aspect;

import com.app.wechat.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice(basePackages = "com.app.wechat")
public class ResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2, Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse response) {
        log.info("接口返回={}", JSONUtil.bean2Json(body));
        return body;
    }
}
