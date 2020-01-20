package com.qsq.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import com.qsq.user.dto.RoleOperatorRequestDTO;
import com.qsq.user.po.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 角色列表
     *
     * @param page
     * @param requestDTO
     * @return
     */
    List<RoleListResponseDTO> getRoleList(Page<RoleListResponseDTO> page, RoleListRequestDTO requestDTO);

    /**
     * 新增角色信息
     *
     * @param requestDTO
     * @param userInfo
     */
    void insertRoleInfo(RoleOperatorRequestDTO requestDTO, UserInfo userInfo);

    /**
     * 更新角色信息
     *
     * @param requestDTO
     * @param userInfo
     */
    void updateRoleInfo(RoleOperatorRequestDTO requestDTO, UserInfo userInfo);
}
