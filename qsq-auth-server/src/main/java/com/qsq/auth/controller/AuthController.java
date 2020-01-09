package com.qsq.auth.controller;

import com.qsq.auth.converter.AuthModuleConverter;
import com.qsq.auth.dto.LoginRequestDTO;
import com.qsq.auth.dto.RegisterRequestDTO;
import com.qsq.auth.dto.UserResponseDTO;
import com.qsq.auth.po.SysUser;
import com.qsq.auth.service.SysUserService;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.auth.model.UserOperator;
import com.qsq.auth.service.AuthServerService;
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
    @PostMapping("/login")
    public ResultResponse login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        UserResponseDTO responseDTO = authServerService.getUserInfoByRequestDTO(requestDTO);
        return ResultResponse.success(responseDTO);
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
//    @PostMapping("/info/{id}")
    @CheckAuth(value = "hasRole('admin')")
//    @CheckAuth
    public ResultResponse getSysUserInfo(@PathVariable(value = "id") Integer id) {
        UserInfo user = userOperator.getUser();
        if (id.compareTo(user.getUserId()) != 0) {
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "你没有权限修改当前用户数据");
        }
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser == null) {
            return ResultResponse.fail("查不到数据");
        } else {
            return ResultResponse.success(sysUser);
        }
    }

    /**
     * 注册用户
     *
     * @param registerRequestDTO
     * @return
     */
    @PostMapping("/register")
    public ResultResponse register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        SysUser sysUser = AuthModuleConverter.registerToSysUser(registerRequestDTO);
        if (sysUserService.save(sysUser)) {
            return ResultResponse.success("注册成功");
        } else {
            return ResultResponse.fail("注册失败");
        }
    }


}