package com.qsq.user.dto;

import com.qsq.user.po.SysPermissions;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/17 19:34
 * No, again
 * 〈用户的全部数据〉
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDTO implements Serializable {

    /**
     * 用户信息
     */
    private SysUser userInfo ;

    /**
     * 角色信息
     */
    private List<SysRole> roleInfoList ;

    /**
     * 用户的权限
     */
    private List<SysPermissions> permissions ;


}