package com.qsq.common.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/6 21:44
 * No, again
 * 〈用户信息保存在程序中对的数据〉
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Integer userId;

    private String username;
    /**
     * 头像
     */
    private String avatar;

    private String nickname;
    /**
     * 角色
     */
    private List<String> roles = new ArrayList<>();

    /**
     * 版本号控制当前jwt的用户信息是否有效
     */
    private String version;


}