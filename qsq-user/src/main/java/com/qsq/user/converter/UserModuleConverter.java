package com.qsq.user.converter;

import com.qsq.common.auth.model.UserInfo;
import com.qsq.user.dto.RegisterRequestDTO;
import com.qsq.user.dto.RoleOperatorRequestDTO;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysRolePermissions;
import com.qsq.user.po.SysUser;
import com.qsq.user.po.SysUserRole;
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

    private final static Integer NORMAL_SIGN = 0;

    /**
     * 新增用户
     *
     * @param requestDTO
     * @param userInfo
     * @return
     */
    public SysUser registerToSysUser(RegisterRequestDTO requestDTO, UserInfo userInfo) {
        SysUser sysUser = SysUser.builder()
                .username(requestDTO.getUserInfo().getUsername())
                .password(requestDTO.getUserInfo().getPassword())
                .nickname(requestDTO.getUserInfo().getNickname())
                .mobile(requestDTO.getUserInfo().getMobile())
                .birthday(requestDTO.getUserInfo().getBirthday())
                .sex(requestDTO.getUserInfo().getSex())
                .email(requestDTO.getUserInfo().getEmail())
                .lockSign(requestDTO.getUserInfo().getLockSign() == null ? NORMAL_SIGN : requestDTO.getUserInfo().getLockSign())
                .address(requestDTO.getUserInfo().getAddress())
                .avatar(StringUtils.isEmpty(requestDTO.getUserInfo().getAvatar()) ? DEFAULT_AVATAR_URI : requestDTO.getUserInfo().getAvatar())
                .build();
        sysUser.setCreateUser(userInfo.getUserId())
                .setCreateTime(new Date());
        return sysUser;
    }

    /**
     * 新增用户
     *
     * @param requestDTO
     * @param userInfo
     * @return
     */
    public SysUser updateUserInfo(RegisterRequestDTO requestDTO, UserInfo userInfo) {
        SysUser sysUser = SysUser.builder()
                .userId(requestDTO.getUserInfo().getUserId())
                .username(requestDTO.getUserInfo().getUsername())
                .password(requestDTO.getUserInfo().getPassword())
                .nickname(requestDTO.getUserInfo().getNickname())
                .mobile(requestDTO.getUserInfo().getMobile())
                .birthday(requestDTO.getUserInfo().getBirthday())
                .sex(requestDTO.getUserInfo().getSex())
                .email(requestDTO.getUserInfo().getEmail())
                .lockSign(requestDTO.getUserInfo().getLockSign() == null ? NORMAL_SIGN : requestDTO.getUserInfo().getLockSign())
                .address(requestDTO.getUserInfo().getAddress())
                .avatar(StringUtils.isEmpty(requestDTO.getUserInfo().getAvatar()) ? DEFAULT_AVATAR_URI : requestDTO.getUserInfo().getAvatar())
                .build();
        sysUser.setUpdateUser(userInfo.getUserId())
                .setUpdateTime(new Date());
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
        sysRole.setRoleName(requestDTO.getRole().getRoleName());
        sysRole.setDescription(requestDTO.getRole().getDescription());
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
        sysRole.setRoleId(requestDTO.getRole().getRoleId());
        sysRole.setRoleName(requestDTO.getRole().getRoleName());
        sysRole.setDescription(requestDTO.getRole().getDescription());
        sysRole.setUpdateTime(new Date());
        sysRole.setUpdateUser(userInfo.getUserId());
        return sysRole;
    }

    public static List<SysUserRole> converterToUserRole(RegisterRequestDTO registerRequestDTO, SysUser sysUser, UserInfo userInfo) {
        List<SysUserRole> roles = new ArrayList<>();
        registerRequestDTO.getRoleInfoList().forEach(role -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId())
                    .setRoleId(role.getRoleId())
                    .setCreateUser(userInfo.getUserId())
                    .setCreateTime(new Date());
            roles.add(sysUserRole);
        });
        return roles;
    }
}