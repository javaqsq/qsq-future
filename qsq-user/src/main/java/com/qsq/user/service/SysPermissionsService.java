package com.qsq.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qsq.user.dto.PermissionListRequestDTO;
import com.qsq.user.dto.PermissionListResponseDTO;
import com.qsq.user.po.SysPermissions;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
public interface SysPermissionsService extends IService<SysPermissions> {

    /**
     * 获取权限列表
     *
     * @param page
     * @param requestDTO
     * @return
     */
    List<PermissionListResponseDTO> getPermissionList(Page<PermissionListResponseDTO> page, PermissionListRequestDTO requestDTO);

    /**
     * 根据角色id获取权限
     *
     * @param id
     * @return
     */
    List<SysPermissions> getPermissionListByRoleId(Integer id);
}
