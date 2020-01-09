package com.qsq.common.auth.annotation.el;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QSQ
 * @create 2020/1/8 10:18
 * No, again
 * 〈〉
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class AuthExpressionResult {

    private String message;

    private Boolean flag;

    public static AuthExpressionResult success() {
        return AuthExpressionResult.builder()
                .message("验证成功")
                .flag(true)
                .build();
    }

    public static AuthExpressionResult fail(String message) {
        return AuthExpressionResult.builder()
                .message(message)
                .flag(false)
                .build();
    }

}