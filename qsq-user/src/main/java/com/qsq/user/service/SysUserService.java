package com.qsq.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.dto.UserInfoResponseDTO;
import com.qsq.user.po.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户列表
     *
     * @param pagePage
     * @param requestDTO
     * @return
     */
    List<SysUserListResponseDTO> getSysUserList(Page<SysUserListResponseDTO> pagePage, SysUserListRequestDTO requestDTO);

    /**
     * 获取用户的全部信息
     *
     * @param id 用户id
     * @return
     */
    UserInfoResponseDTO getUserInfo(Integer id);


}
