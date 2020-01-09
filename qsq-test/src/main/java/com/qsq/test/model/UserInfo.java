package com.qsq.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author QSQ
 * @create 2019/12/29 17:31
 * No, again
 * 〈用户信息〉
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements Serializable {

    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 角色
     */
    private List<String> roles;
    /**
     * 权限
     */
    private List<String> permissions;


}