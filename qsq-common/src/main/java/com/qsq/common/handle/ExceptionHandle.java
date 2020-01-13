package com.qsq.common.handle;

import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.exception.BusinessRuntimeException;
import com.qsq.common.model.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

/**
 * @author QSQ
 * @create 2019/10/16 20:30
 * No, again
 * 〈控制层的统一异常返回〉
 */
@ControllerAdvice(basePackages = {"com.qsq"})
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultResponse system(Exception e) {
        log.error(" 系统错误 ", e);
        return converterResultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(value = MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultResponse notFoundException(MissingPathVariableException response) {
        return converterResultResponse(HttpStatus.BAD_REQUEST.value(), response.getMessage());
    }


    /**
     * 404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResultResponse handleValidationException(ValidationException e) {
        log.error(" 入参参数错误 ", e);
        return converterResultResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     *
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(" 入参参数缺失 ", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String errorDefaultMessage = error.getDefaultMessage();
        return converterResultResponse(HttpStatus.BAD_REQUEST.value(), errorDefaultMessage);
    }

    /**
     * 系统业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultResponse businessError(BusinessRuntimeException e) {
        log.error(" 自定义异常出错 ", e);
        return converterResultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 权限异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthSecurityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultResponse businessError(AuthSecurityException e) {
        log.error(" 认证出错 ", e);
        return converterResultResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }


    private ResultResponse converterResultResponse(Integer code, String errorMessage) {
        return ResultResponse.fail(code, errorMessage);
    }

}