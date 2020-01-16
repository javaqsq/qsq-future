package com.qsq.user.po;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.qsq.common.model.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


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


}
