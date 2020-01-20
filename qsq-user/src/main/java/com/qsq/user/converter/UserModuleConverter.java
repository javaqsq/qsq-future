package com.qsq.user.converter;

import com.qsq.common.auth.model.UserInfo;
import com.qsq.user.dto.RegisterRequestDTO;
import com.qsq.user.dto.RoleOperatorRequestDTO;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysRolePermissions;
import com.qsq.user.po.SysUser;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/7 16:12
 * No, again
 * 〈〉
 */
@UtilityClass
public class UserModuleConverter {

    private final static String DEFAULT_AVATAR_URI = "E:/java-code/future-project-shop/file-path/avatar/20200113225310dota2剑圣.jpg";

    /**
     * 转换
     *
     * @param requestDTO
     * @param userInfo
     * @return
     */
    public SysUser registerToSysUser(RegisterRequestDTO requestDTO, UserInfo userInfo) {
        SysUser sysUser = SysUser.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .nickname(requestDTO.getNickname())
                .mobile(requestDTO.getMobile())
                .birthday(requestDTO.getBirthday())
                .sex(requestDTO.getSex())
                .email(requestDTO.getEmail())
                .lockSign(requestDTO.getLockSign())
                .address(requestDTO.getAddress())
                .avatar(StringUtils.isEmpty(requestDTO.getAvatar()) ? DEFAULT_AVATAR_URI : requestDTO.getAvatar())
                .build();
        sysUser.setCreateUser(userInfo.getUserId());
        return sysUser;
    }

    /**
     * 角色新增
     *
     * @param requestDTO
     * @param userInfo
     * @return
     */
    public static SysRole converterRoleInsert(RoleOperatorRequestDTO requestDTO, UserInfo userInfo) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(requestDTO.getRoleName());
        sysRole.setDescription(requestDTO.getDescription());
        sysRole.setCreateTime(new Date());
        sysRole.setCreateUser(userInfo.getUserId());
        return sysRole;
    }

    public static List<SysRolePermissions> converterInsert(RoleOperatorRequestDTO requestDTO, Integer roleId, UserInfo userInfo) {
        List<SysRolePermissions> rolePermissions = new ArrayList<>();
        requestDTO.getPermissions().forEach(permissions -> {
            SysRolePermissions build = new SysRolePermissions();
            build.setPermissionsId(permissions.getPermissionsId())
                    .setRoleId(roleId)
                    .setCreateTime(new Date())
                    .setCreateUser(userInfo.getUserId());
            rolePermissions.add(build);
        });
        return rolePermissions;
    }


    public static SysRole converterRoleUpdate(RoleOperatorRequestDTO requestDTO, UserInfo userInfo) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(requestDTO.getRoleId());
        sysRole.setRoleName(requestDTO.getRoleName());
        sysRole.setDescription(requestDTO.getDescription());
        sysRole.setUpdateTime(new Date());
        sysRole.setUpdateUser(userInfo.getUserId());
        return sysRole;
    }
}