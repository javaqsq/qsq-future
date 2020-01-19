package com.qsq.user.dto;

import com.qsq.user.po.SysRole;
import lombok.Data;

/**
 * @author QSQ
 * @create 2020/1/19 22:41
 * No, again
 * 〈〉
 */
@Data
public class RoleListResponseDTO extends SysRole {

    private String createUserName;

}