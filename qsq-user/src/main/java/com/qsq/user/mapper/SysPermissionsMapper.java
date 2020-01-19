package com.qsq.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.user.dto.PermissionListRequestDTO;
import com.qsq.user.dto.PermissionListResponseDTO;
import com.qsq.user.po.SysPermissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
public interface SysPermissionsMapper extends BaseMapper<SysPermissions> {

    /**
     * 权限列表
     *
     *
     * @param page
     * @param requestDTO
     * @return
     */
    List<PermissionListResponseDTO> getPermissionList(Page<PermissionListResponseDTO> page, @Param("requestDTO") PermissionListRequestDTO requestDTO);
}
