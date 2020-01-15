package com.qsq.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.po.SysUser;
import com.qsq.user.mapper.SysUserMapper;
import com.qsq.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 用户列表
     *
     *
     * @param pagePage
     * @param requestDTO
     * @return
     */
    @Override
    public List<SysUserListResponseDTO> sysUserList(Page<SysUserListResponseDTO> pagePage, SysUserListRequestDTO requestDTO) {
        return this.baseMapper.sysUserList(pagePage,requestDTO);
    }
}
