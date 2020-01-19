package com.qsq.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import com.qsq.user.po.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 角色列表
     *
     *
     * @param page
     * @param requestDTO
     * @return
     */
    List<RoleListResponseDTO> getRoleList(Page<RoleListResponseDTO> page, @Param("requestDTO") RoleListRequestDTO requestDTO);
}
