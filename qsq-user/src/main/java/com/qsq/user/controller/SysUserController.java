package com.qsq.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.auth.enums.AuthSecurityEnum;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.converter.UserModuleConverter;
import com.qsq.user.dto.RegisterRequestDTO;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.dto.UserInfoResponseDTO;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import com.qsq.common.model.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/user/admin")
public class SysUserController extends BaseController<SysUserService, SysUser> {


    /**
     * 用户列表
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/list")
    @CheckAuth("hasRole('admin')")
    public ResultResponse list(@RequestBody SysUserListRequestDTO requestDTO) {
        Page<SysUserListResponseDTO> page = super.converterDTOToPageInit(requestDTO);
        return ResultResponse.successPage(page, service.getSysUserList(page, requestDTO));
    }

    @GetMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse info(@PathVariable("id") Integer id) {
        UserInfoResponseDTO userInfo = service.getUserInfo(id);
        return ResultResponse.success(userInfo);
    }

    /**
     * 注册用户
     *
     * @param registerRequestDTO
     * @return
     */
    @PostMapping("/register")
    @CheckAuth("hasRole('admin')")
    public ResultResponse register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, HttpServletRequest request) {
        // 验证username
        QueryWrapper<SysUser> usernameWrapper = new QueryWrapper<SysUser>()
                .eq("username", registerRequestDTO.getUsername());
        SysUser usernameUser = service.getOne(usernameWrapper);
        if (usernameUser != null) {
            throw AuthSecurityEnum.USERNAME_ALREADY_EXIST.getException();
        }
        QueryWrapper<SysUser> mobileWrapper = new QueryWrapper<SysUser>()
                .eq("mobile", registerRequestDTO.getMobile());
        SysUser mobileUser = service.getOne(mobileWrapper);
        if (mobileUser != null) {
            throw AuthSecurityEnum.MOBILE_ALREADY_EXIST.getException();
        }
        UserInfo userInfo = (UserInfo)request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        SysUser sysUser = UserModuleConverter.registerToSysUser(registerRequestDTO,userInfo);
        if (service.save(sysUser)) {
            return ResultResponse.success("注册成功");
        } else {
            return ResultResponse.fail("注册失败");
        }
    }


}

