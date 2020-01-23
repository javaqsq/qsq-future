package com.qsq.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.user.converter.UserModuleConverter;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import com.qsq.user.dto.RoleOperatorRequestDTO;
import com.qsq.user.mapper.SysRoleMapper;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysRolePermissions;
import com.qsq.user.service.SysPermissionsService;
import com.qsq.user.service.SysRolePermissionsService;
import com.qsq.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Autowired
    private SysPermissionsService sysPermissionsService;

    @Autowired
    private SysRolePermissionsService rolePermissionsService;

    /**
     * 角色列表
     *
     * @param page
     * @param requestDTO
     * @return
     */
    @Override
    public List<RoleListResponseDTO> getRoleList(Page<RoleListResponseDTO> page, RoleListRequestDTO requestDTO) {
        return this.baseMapper.getRoleList(page, requestDTO);
    }

    /**
     * 新增角色信息
     *
     * @param requestDTO
     * @param userInfo
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insertRoleInfo(RoleOperatorRequestDTO requestDTO, UserInfo userInfo) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("role_name", requestDTO.getRole().getRoleName())
                .or()
                .eq("description", requestDTO.getRole().getDescription());
        SysRole sysRole = this.baseMapper.selectOne(query);
        if (sysRole != null) {
            throw ExceptionEnum.ROLE_NAME_MISS.getException();
        }
        SysRole entity = UserModuleConverter.converterRoleInsert(requestDTO, userInfo);
        this.save(entity);
        //新增关系表
        List<SysRolePermissions> rolePermissions = UserModuleConverter.converterInsert(requestDTO, entity.getRoleId(), userInfo);
        rolePermissionsService.saveBatch(rolePermissions);
    }

    /**
     * 更新角色信息
     *
     * @param requestDTO
     * @param userInfo
     */
    @Override
    public void updateRoleInfo(RoleOperatorRequestDTO requestDTO, UserInfo userInfo) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("role_name", requestDTO.getRole().getRoleName())
                .or()
                .eq("description", requestDTO.getRole().getDescription());
        SysRole sysRole = this.baseMapper.selectOne(query);
        if (sysRole != null && sysRole.getRoleId().compareTo(requestDTO.getRole().getRoleId()) != 0) {
            throw ExceptionEnum.ROLE_NAME_MISS.getException();
        }
        SysRole entity = UserModuleConverter.converterRoleUpdate(requestDTO, userInfo);
        //先删除旧的权限
        QueryWrapper<SysRolePermissions> permissionsQuery = new QueryWrapper<>();
        permissionsQuery.eq("role_id", entity.getRoleId());
        rolePermissionsService.remove(permissionsQuery);
        //更新 ,新增
        this.updateById(entity);
        List<SysRolePermissions> rolePermissions = UserModuleConverter.converterInsert(requestDTO, entity.getRoleId(), userInfo);
        rolePermissionsService.saveBatch(rolePermissions);
    }

}
