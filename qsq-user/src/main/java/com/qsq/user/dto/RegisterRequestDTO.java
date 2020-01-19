package com.qsq.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author QSQ
 * @create 2020/1/6 21:46
 * No, again
 * 〈登录请求〉
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO implements Serializable {


    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 生日
     * 如果是时间的话 ， 加上
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别:1:男  ;0:女
     */
    private Integer sex;

    /**
     * 是否锁定 : 1:锁定 ; 0:正常
     */
    private Integer lockSign;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String avatar;
}