package com.qsq.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.qsq.auth.dto.LoginRequestDTO;
import com.qsq.auth.dto.UserResponseDTO;
import com.qsq.auth.mapper.AuthServerMapper;
import com.qsq.auth.mapper.SysUserMapper;
import com.qsq.auth.po.SysUser;
import com.qsq.auth.service.AuthServerService;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.jwt.JwtOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author QSQ
 * @create 2020/1/7 9:18
 * No, again
 * 〈〉
 */
@Service
public class AuthServerServiceImpl implements AuthServerService {

    @Autowired
    private AuthServerMapper authServerMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JwtOperator jwtOperator;


    /**
     * 用户授权 ， 登录
     *
     * @param requestDTO
     * @return
     */
    @Override
    public UserResponseDTO getUserInfoByRequestDTO(LoginRequestDTO requestDTO) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<SysUser>()
                .eq("username", requestDTO.getUsername())
                .eq("password", requestDTO.getPassword());
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        if (sysUser == null) {
            throw ExceptionEnum.LOGIN_USER_NAME_OR_PWD_ERROR.getException();
        }
        List<String> roles = authServerMapper.getUserRoleByUserId(sysUser.getUserId());
        Map<String, Object> claims = initTokenMap(sysUser, roles);
        String token = jwtOperator.generateToken(claims);
        return UserResponseDTO.builder()
                .userId(sysUser.getUserId())
                .username(sysUser.getUsername())
                .token(token)
                .nickname(sysUser.getNickname())
                .roles(roles)
                .build();
    }


    /**
     * 这个是封装保存给jwt的数据
     *
     * @param sysUser
     * @param roles
     * @return
     */
    private Map<String, Object> initTokenMap(SysUser sysUser, List<String> roles) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", sysUser.getUsername());
        map.put("userId", sysUser.getUserId());
        map.put("nickname", sysUser.getNickname());
        map.put("roles", roles);
        return map;
    }
}