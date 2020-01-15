package com.qsq.user.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.model.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QSQ
 * @create 2020/1/15 19:50
 * No, again
 * 〈用户列表请求参数〉
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserListRequestDTO extends PageQuery {

    private String username;

    private String role;

    private String nickname;

}