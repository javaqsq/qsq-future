package com.qsq.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.po.SysRole;
import com.qsq.user.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户列表
     *
     * @param pagePage
     * @param requestDTO
     * @return
     */
    List<SysUserListResponseDTO> getSysUserList(Page<SysUserListResponseDTO> pagePage, @Param("requestDTO") SysUserListRequestDTO requestDTO);

    /**
     * 获取用户角色信息
     *
     * @param id
     * @return
     */
    List<SysRole> getUserRoleInfoByUserId(@Param("id") Integer id);

    /**
     * 获取角色的权限信息
     *
     * @param id
     * @return
     */
    List<SysPermissions> getUserPermissionsByUserId(@Param("id") int id);


}
