package com.qsq.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.dto.UserInfoResponseDTO;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysUser;
import com.qsq.user.mapper.SysUserMapper;
import com.qsq.user.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysPermissionsService permissionsService;
    @Autowired
    private SysRolePermissionsService rolePermissionsService;

    /**
     * 用户列表
     *
     * @param pagePage
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<SysUserListResponseDTO> getSysUserList(Page<SysUserListResponseDTO> pagePage, SysUserListRequestDTO requestDTO) {
        return this.baseMapper.getSysUserList(pagePage, requestDTO);
    }

    /**
     * 获取用户的全部信息
     *
     * @param id 用户id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserInfoResponseDTO getUserInfo(Integer id) {
        SysUser sysUser = this.getById(id);
        if (sysUser == null) {
            throw ExceptionEnum.LOGIN_USER_NOT_EXIST.getException();
        }
        List<SysRole> roleInfoList = this.getBaseMapper().getUserRoleInfoByUserId(id);
        List<SysPermissions> permissions = baseMapper.getUserPermissionsByUserId(id);
        return UserInfoResponseDTO.builder()
                .userInfo(sysUser)
                .roleInfoList(roleInfoList)
                .permissions(permissions)
                .build();
    }
}
