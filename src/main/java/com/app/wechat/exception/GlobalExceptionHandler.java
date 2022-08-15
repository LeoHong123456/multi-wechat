package com.app.wechat.exception;

import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.domain.base.FailResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Objects;

/**
 * @author admin
 * @description 全局异常
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public FailResult handleBusinessException(BusinessException ex) {
        log.error("Method execution business exception!", ex);
        return FailResult.failure(ex.getEnumName(), ex.getLocalizedMessage());
    }

    /**
     * 请求参数不符合规则校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public FailResult handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("request param exception!", ex);
        return new FailResult(RestCodeEnum.GLOBAL_FAIL.code(), RestCodeEnum.GLOBAL_FAIL.message());
    }

    /**
     * GET请求参数校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("request param exception!", ex);
        String messageEnum = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        if (StringUtils.containsIgnoreCase(messageEnum, "Failed to convert property value of type")) {
            return FailResult.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR.name(), ex.getLocalizedMessage());
        } else {
            return FailResult.failure(messageEnum, ex.getLocalizedMessage());
        }
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Object BindException(BindException ex) {
        log.error("request param exception!", ex);
        String messageEnum = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        if (StringUtils.containsIgnoreCase(messageEnum, "Failed to convert property value of type")) {
            return FailResult.failure(RestCodeEnum.GLOBAL_FAIL.name(), ex.getLocalizedMessage());
        } else {
            return FailResult.failure(messageEnum, ex.getLocalizedMessage());
        }
    }

    /**
     * 请求参数类型校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public FailResult UnexpectedTypeException(UnexpectedTypeException ex) {
        log.error("request param exception!", ex);
        return FailResult.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR.name(), ex.getLocalizedMessage());
    }


    /**
     * 404异常封装
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public FailResult NoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("404 NoHandlerFound exception!", ex);
        return FailResult.failure(RestCodeEnum.FAIL_TO_NO_HANDLER_FOUND.name(), ex.getLocalizedMessage());
    }
}
