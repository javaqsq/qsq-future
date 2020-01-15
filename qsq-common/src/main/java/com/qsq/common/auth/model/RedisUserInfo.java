package com.qsq.common.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/14 10:34
 * No, again
 * 〈redis中保存用户信息的数据〉
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisUserInfo {


    private String token;

    /**
     * 版本号控制当前redis的用户信息是否有效
     */
    private String version;
}