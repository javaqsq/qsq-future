package com.qsq.common.exception;

import lombok.Data;

/**
 * @author QSQ
 * @create 2020/1/6 22:04
 * No, again
 * 〈〉
 */
@Data
public class AuthSecurityException extends RuntimeException {

    /**
     * 错误代码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;


    public AuthSecurityException() {
        super();
    }

    public AuthSecurityException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
