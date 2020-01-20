package com.qsq.user.dto;

import com.qsq.common.auth.model.UserInfo;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.po.SysRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/20 22:25
 * No, again
 * 〈〉
 */
@Data
public class RoleOperatorRequestDTO implements Serializable {

    private Integer roleId;

    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @NotBlank(message = "角色描述")
    private String description;

    List<SysPermissions> permissions;


}