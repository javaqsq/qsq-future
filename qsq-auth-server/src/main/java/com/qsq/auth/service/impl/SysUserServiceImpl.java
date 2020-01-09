package com.qsq.auth.service.impl;

import com.qsq.auth.po.SysUser;
import com.qsq.auth.mapper.SysUserMapper;
import com.qsq.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
