package com.qsq.user.dto;

import com.qsq.user.po.SysPermissions;
import com.qsq.user.po.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/20 22:42
 * No, again
 * 〈〉
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfoResponse implements Serializable {

    private SysRole role;

    private List<SysPermissions> permissions;

}