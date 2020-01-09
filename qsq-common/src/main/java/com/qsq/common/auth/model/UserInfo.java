package com.qsq.common.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer userId ;

    private String username ;

    private String nickname ;
    /**
     * 角色
     */
    private List<String> roles ;





}