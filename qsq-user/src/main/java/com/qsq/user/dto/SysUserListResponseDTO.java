package com.qsq.user.dto;

import com.qsq.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/15 19:49
 * No, again
 * 〈用户管理返回参数〉
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserListResponseDTO  extends BaseEntity {

    private Integer userId;

    /**
     * 用户名
     */
    private String username;


    /**
     * 头像
     */
    private String avatar;

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
     */
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


    private String roles;

    private String roleNames;

    private String createUserName;

}