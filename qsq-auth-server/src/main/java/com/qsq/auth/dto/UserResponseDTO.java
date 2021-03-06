package com.qsq.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/6 21:47
 * No, again
 * 〈用户数据返回给前端对的数据〉
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO implements Serializable {

    private Integer userId ;

    private String username ;

    private String nickname ;

    /**
     * 头像
     */
    private String avatar;

    private String token ;

    private List<String> roles ;

}