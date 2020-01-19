package com.qsq.user.dto;

import com.qsq.user.po.SysPermissions;
import lombok.Data;

/**
 * @author QSQ
 * @create 2020/1/19 22:40
 * No, again
 * 〈〉
 */
@Data
public class PermissionListResponseDTO extends SysPermissions {

    private String createUserName ;

}