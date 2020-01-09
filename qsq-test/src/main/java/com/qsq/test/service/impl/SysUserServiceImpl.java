package com.qsq.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.test.dto.LoginRequestDTO;
import com.qsq.test.mapper.SysUserMapper;
import com.qsq.test.po.SysUser;
import com.qsq.test.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2019-12-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser loginByUsernameAndPassword(LoginRequestDTO loginRequestDTO) {
        SysUser sysUser = this.baseMapper.selectOne(
                new QueryWrapper<SysUser>()
                        .eq("username", loginRequestDTO.getUsername())
                        .eq("password", loginRequestDTO.getPassword()));
        if (sysUser == null) {
            throw ExceptionEnum.LOGIN_USER_NOT_EXIST.getException();
        }
        sysUser.setPassword(null).setEmail(null).setMobile(null);
        return sysUser;
    }

}
