package com.app.wechat.exception;
import com.app.wechat.domain.base.FailResult;
import com.app.wechat.domain.enums.RestCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

/**
 * @author admin
 * @description 全局异常
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public FailResult handleBusinessException(BusinessException ex) {
        log.error("Method execution business exception!", ex);
        return FailResult.failure(ex.getCode(), ex.getLocalizedMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public FailResult handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("request param exception!", ex);
        return new FailResult(RestCodeEnum.GLOBAL_FAIL.code(), RestCodeEnum.GLOBAL_FAIL.message());
    }
    @ExceptionHandler(RedisConnectionFailureException.class)
    public FailResult handleRedisConnectionFailureException(RedisConnectionFailureException ex) {
        log.error("redis connection exception!", ex);
        return new FailResult(RestCodeEnum.FAIL_TO_REDIS_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("request param exception!", ex);
        return FailResult.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR.code(), ex.getLocalizedMessage());
    }
    @ExceptionHandler(BindException.class)
    public Object BindException(BindException ex) {
        log.error("request param exception!", ex);
        String defaultMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return FailResult.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR.code(), defaultMessage);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public FailResult UnexpectedTypeException(UnexpectedTypeException ex) {
        log.error("request param exception!", ex);
        return FailResult.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR.code(), ex.getMessage());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public FailResult NoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("404 NoHandlerFound exception!", ex);
        return FailResult.failure(RestCodeEnum.FAIL_TO_NO_HANDLER_FOUND.code(), ex.getLocalizedMessage());
    }
}
