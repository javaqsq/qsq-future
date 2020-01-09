package com.qsq.test.service;

import com.qsq.test.dto.LoginRequestDTO;
import com.qsq.test.po.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qsq
 * @since 2019-12-29
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名等于
     *
     * @param loginRequestDTO
     * @return SysUser
     */
    SysUser loginByUsernameAndPassword(LoginRequestDTO loginRequestDTO);
}
