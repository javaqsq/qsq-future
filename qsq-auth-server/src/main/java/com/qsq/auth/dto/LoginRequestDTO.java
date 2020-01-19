package com.qsq.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author QSQ
 * @create 2020/1/6 21:46
 * No, again
 * 〈登录请求〉
 */
@Data
public class LoginRequestDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;


}