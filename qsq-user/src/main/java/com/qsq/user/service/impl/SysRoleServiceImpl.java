package com.qsq.user.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import com.qsq.user.mapper.SysRoleMapper;
import com.qsq.user.po.SysRole;
import com.qsq.user.service.SysRoleService;
import org.springframework.stereotype.Service;

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
}
