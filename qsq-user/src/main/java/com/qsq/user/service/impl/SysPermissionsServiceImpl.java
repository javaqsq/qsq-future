package com.qsq.user.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsq.user.dto.PermissionListRequestDTO;
import com.qsq.user.dto.PermissionListResponseDTO;
import com.qsq.user.mapper.SysPermissionsMapper;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.service.SysPermissionsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Service
public class SysPermissionsServiceImpl extends ServiceImpl<SysPermissionsMapper, SysPermissions> implements SysPermissionsService {

    /**
     * 权限列表
     *
     * @param page
     * @param requestDTO
     * @return
     */
    @Override
    public List<PermissionListResponseDTO> getPermissionList(Page<PermissionListResponseDTO> page, PermissionListRequestDTO requestDTO) {
        return this.baseMapper.getPermissionList(page, requestDTO);
    }

    /**
     * 根据角色id获取权限
     *
     * @param id
     * @return
     */
    @Override
    public List<SysPermissions> getPermissionListByRoleId(Integer id) {
        return this.baseMapper.getPermissionListByRoleId(id);
    }
}
