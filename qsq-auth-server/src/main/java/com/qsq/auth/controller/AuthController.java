package com.qsq.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qsq.auth.converter.AuthModuleConverter;
import com.qsq.auth.dto.LoginRequestDTO;
import com.qsq.auth.dto.RegisterRequestDTO;
import com.qsq.auth.dto.UserResponseDTO;
import com.qsq.auth.po.SysUser;
import com.qsq.auth.service.SysUserService;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.enums.AuthSecurityEnum;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.auth.model.UserOperator;
import com.qsq.auth.service.AuthServerService;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.model.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author QSQ
 * @create 2020/1/6 21:36
 * No, again
 * 〈认证授权控制类入口〉
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServerService authServerService;

    @Autowired
    private UserOperator userOperator;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/admin/login")
    public ResultResponse login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        UserResponseDTO responseDTO = authServerService.getUserInfoByRequestDTO(requestDTO);
        return ResultResponse.success(HttpStatus.OK.value(), "登录成功", responseDTO);
    }

    /**
     * 用户登录，授权
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/token")
    public ResultResponse token(@RequestBody @Valid LoginRequestDTO requestDTO) {
        UserResponseDTO responseDTO = authServerService.getUserInfoByRequestDTO(requestDTO);
        return ResultResponse.success(responseDTO.getToken());
    }

    /**
     * 获取用户详情
     *
     * @return
     */
    @PostMapping("/info")
    public ResultResponse getSysUserInfo(@RequestParam("token") String token) {
        UserInfo userInfo = userOperator.getUserFromToken(token);
        if (userInfo == null) {
            throw ExceptionEnum.LOGIN_USER_NOT_EXIST.getException();
        }
        return ResultResponse.success(userInfo);
    }

    /**
     * 注册用户
     *
     * @param registerRequestDTO
     * @return
     */
    @PostMapping("/register")
    public ResultResponse register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        // 验证username
        QueryWrapper<SysUser> usernameWrapper = new QueryWrapper<SysUser>()
                .eq("username", registerRequestDTO.getUsername());
        SysUser usernameUser = sysUserService.getOne(usernameWrapper);
        if (usernameUser != null) {
            throw AuthSecurityEnum.USERNAME_ALREADY_EXIST.getException();
        }
        QueryWrapper<SysUser> mobileWrapper = new QueryWrapper<SysUser>()
                .eq("mobile", registerRequestDTO.getMobile());
        SysUser mobileUser = sysUserService.getOne(mobileWrapper);
        if (mobileUser != null) {
            throw AuthSecurityEnum.MOBILE_ALREADY_EXIST.getException();
        }
        SysUser sysUser = AuthModuleConverter.registerToSysUser(registerRequestDTO);
        if (sysUserService.save(sysUser)) {
            return ResultResponse.success("注册成功");
        } else {
            return ResultResponse.fail("注册失败");
        }
    }

    /**
     * 登出,退出
     *
     * @return
     */
    @PostMapping("/logout")
    @CheckAuth("hasLogin()")
    public ResultResponse logout() {
        authServerService.logout();
        return ResultResponse.success();
    }


}