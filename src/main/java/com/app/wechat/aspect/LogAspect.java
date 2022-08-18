package com.app.wechat.aspect;

import com.alibaba.fastjson.JSONObject;
import com.app.wechat.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author leo
 * @description 系统全局日志打印
 */

@Slf4j
@Aspect
@Order(10)
@Component
public class LogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.app.wechat.controller.*.*(..))")
    public void printLog() {
    }

    @Before("printLog()")
    public void before(JoinPoint joinPoint) {
        log.info("==================开始处理请求=====================>");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String ip = RequestUtil.getIpAddr(request);
        String method = request.getMethod();
        String path = request.getRequestURL().toString();
        String header = request.getHeader("User-Agent");
        String contentType = request.getContentType();
        String requestArgs = null;
        if(!StringUtils.contains(contentType,"multipart/form-data")){
            requestArgs = JSONObject.toJSONString(joinPoint.getArgs());
        }

        log.info("请求来源:{}", ip);
        log.info("请求URL:{}", path);
        log.info("请求方式:{}", method);
        log.info("请求头部:{}", header);
        log.info("请求类型:{}", contentType);
        log.info("请求参数:{}", requestArgs);
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "printLog()", returning = "response")
    public void returning(JoinPoint joinPoint, Object response) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String servletPath = request.getServletPath();
        String method = StringUtils.substringAfter(servletPath, "/");
        log.info("处理请求[{}]耗时(毫秒):{}", method, System.currentTimeMillis() - startTime.get());
    }
}
