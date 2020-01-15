package com.qsq.common.model;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.enums.ResultEnum;
import com.qsq.common.uitl.IDGenerator;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author QSQ
 * @create 2019/10/16 20:31
 * No, again
 * 〈 返回参数 〉
 */
@Data
public class ResultResponse implements Serializable {

    private boolean success = true;
    private Integer code = 200;
    private String message = "成功";
    private String requestId = IDGenerator.getUUID();
    private Object data;

    public ResultResponse() {
    }

    public ResultResponse(boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultResponse fail() {
        return new ResultResponse(false, ResultEnum.OPERATE_FAIL.getCode(), ResultEnum.OPERATE_FAIL.getValue(), null);
    }

    public static ResultResponse fail(String message) {
        return new ResultResponse(false, ResultEnum.OPERATE_FAIL.getCode(), message, null);
    }

    public static ResultResponse fail(Integer code, String message) {
        return new ResultResponse(false, code, message, null);
    }

    public static ResultResponse fail(ExceptionEnum exceptionEnum) {
        return new ResultResponse(false, exceptionEnum.getCode(), exceptionEnum.getValue(), null);
    }

    public static ResultResponse success() {
        return new ResultResponse(true, ResultEnum.OPERATE_SUCCESS.getCode(), ResultEnum.OPERATE_SUCCESS.getValue(), null);
    }

    public static ResultResponse success(Object data) {
        return new ResultResponse(true, ResultEnum.OPERATE_SUCCESS.getCode(), ResultEnum.OPERATE_SUCCESS.getValue(), data);
    }

    public static ResultResponse success(Integer code, String message) {
        return new ResultResponse(true, code, message, null);
    }

    public static ResultResponse success(Integer code, String message, Object data) {
        return new ResultResponse(true, code, message, data);
    }

    public static ResultResponse returnByEnum(ResultEnum resultEnum) {
        return new ResultResponse(true, resultEnum.getCode(), resultEnum.getValue(), null);
    }

    public static ResultResponse returnByEnum(ResultEnum resultEnum, Object data) {
        return new ResultResponse(true, resultEnum.getCode(), resultEnum.getValue(), data);
    }

    public static <T> ResultResponse successPage(Page<T> page ,List<T> list){
        return new ResultResponse(true, ResultEnum.OPERATE_SUCCESS.getCode(),
                ResultEnum.OPERATE_SUCCESS.getValue(), page.setRecords(list));
    }

}