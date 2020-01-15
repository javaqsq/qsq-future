package com.qsq.auth.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.qsq.auth.dto.LoginRequestDTO;
import com.qsq.auth.dto.UserResponseDTO;
import com.qsq.auth.mapper.AuthServerMapper;
import com.qsq.auth.mapper.SysUserMapper;
import com.qsq.auth.po.SysUser;
import com.qsq.auth.service.AuthServerService;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.auth.model.RedisUserInfo;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.auth.model.UserOperator;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.jwt.JwtOperator;
import com.qsq.common.uitl.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author QSQ
 * @create 2020/1/7 9:18
 * No, again
 * 〈〉
 */
@Service
@Slf4j
public class AuthServerServiceImpl implements AuthServerService {

    @Autowired
    private AuthServerMapper authServerMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JwtOperator jwtOperator;

    @Autowired
    private UserOperator userOperator;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 用户授权 ， 登录
     *
     * @param requestDTO
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
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
        String version = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> claims = initTokenMap(sysUser, roles, version);
        String token = jwtOperator.generateToken(claims);
        RedisUserInfo redisUserInfo = RedisUserInfo.builder()
                .token(token)
                .version(version)
                .build();
        // 默认保存两周 , 如果这边改了 , jwt也得改 ,还需要优化
        redisUtils.set(ConstantsSecurity.TOKEN_PREFIX + sysUser.getUsername(), JSONUtil.toJsonStr(redisUserInfo), ConstantsSecurity.EXPIRE_TIME, TimeUnit.SECONDS);
        log.info(" 用户{} ,登录成功 ! token : {}", sysUser.getUsername(), token);
        return UserResponseDTO.builder()
                .userId(sysUser.getUserId())
                .username(sysUser.getUsername())
                .token(token)
                .avatar(sysUser.getAvatar())
                .nickname(sysUser.getNickname())
                .roles(roles)
                .build();
    }

    /**
     * 登出
     */
    @Override
    public void logout() {
        UserInfo user = userOperator.getUser();
        redisUtils.delete(ConstantsSecurity.TOKEN_PREFIX + user.getUsername());
    }


    /**
     * 这个是封装保存给jwt的数据
     *
     * @param sysUser 用户数据
     * @param roles   角色信息
     * @param version 版本号
     * @return
     */
    private Map<String, Object> initTokenMap(SysUser sysUser, List<String> roles, String version) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", sysUser.getUsername());
        map.put("userId", sysUser.getUserId());
        map.put("nickname", sysUser.getNickname());
        map.put("avatar", sysUser.getAvatar());
        map.put("roles", roles);
        map.put("version", version);
        return map;
    }
}