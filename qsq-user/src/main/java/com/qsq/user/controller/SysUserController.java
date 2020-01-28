package com.qsq.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.auth.enums.AuthSecurityEnum;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.enums.ResultEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.converter.UserModuleConverter;
import com.qsq.user.dto.RegisterRequestDTO;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import com.qsq.user.dto.UserInfoResponseDTO;
import com.qsq.user.po.SysUserRole;
import com.qsq.user.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import com.qsq.common.model.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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


    @Autowired
    private SysUserRoleService userRoleService;

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
     * 新增用户
     *
     * @param registerRequestDTO
     * @return
     */
    @PostMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, HttpServletRequest request) {
        // 验证username
        QueryWrapper<SysUser> usernameWrapper = new QueryWrapper<SysUser>()
                .eq("username", registerRequestDTO.getUserInfo().getUsername());
        SysUser usernameUser = service.getOne(usernameWrapper);
        if (usernameUser != null) {
            throw AuthSecurityEnum.USERNAME_ALREADY_EXIST.getException();
        }
        QueryWrapper<SysUser> mobileWrapper = new QueryWrapper<SysUser>()
                .eq("mobile", registerRequestDTO.getUserInfo().getMobile());
        SysUser mobileUser = service.getOne(mobileWrapper);
        if (mobileUser != null) {
            throw AuthSecurityEnum.MOBILE_ALREADY_EXIST.getException();
        }
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        SysUser sysUser = UserModuleConverter.registerToSysUser(registerRequestDTO, userInfo);
        boolean save = service.save(sysUser);
        List<SysUserRole> roles = UserModuleConverter.converterToUserRole(registerRequestDTO, sysUser, userInfo);
        userRoleService.saveBatch(roles);
        if (save) {
            return ResultResponse.success("注册成功");
        } else {
            return ResultResponse.fail("注册失败");
        }
    }


    @DeleteMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse delete(@PathVariable("id") Integer id) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        userRoleService.remove(queryWrapper);
        service.removeById(id);
        return ResultResponse.returnByEnum(ResultEnum.POJO_DELETE_SUCCESS);
    }


    @PutMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse update(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        // 验证username
        QueryWrapper<SysUser> usernameWrapper = new QueryWrapper<SysUser>()
                .eq("username", registerRequestDTO.getUserInfo().getUsername());
        SysUser usernameUser = service.getOne(usernameWrapper);
        boolean flag = usernameUser != null && userInfo.getUserId().compareTo(registerRequestDTO.getUserInfo().getUserId()) != 0;
        if (flag && super.isHaveAdminRole(userInfo.getRoles())) {
            throw AuthSecurityEnum.USERNAME_ALREADY_EXIST.getException();
        }
        QueryWrapper<SysUser> mobileWrapper = new QueryWrapper<SysUser>()
                .eq("mobile", registerRequestDTO.getUserInfo().getMobile());
        SysUser mobileUser = service.getOne(mobileWrapper);
        boolean mobileFlag = mobileUser != null && userInfo.getUserId().compareTo(registerRequestDTO.getUserInfo().getUserId()) != 0;
        if (mobileFlag && super.isHaveAdminRole(userInfo.getRoles())) {
            throw AuthSecurityEnum.MOBILE_ALREADY_EXIST.getException();
        }
        SysUser sysUser = UserModuleConverter.updateUserInfo(registerRequestDTO, userInfo);
        service.updateById(sysUser);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", sysUser.getUserId());
        userRoleService.remove(queryWrapper);
        List<SysUserRole> roles = UserModuleConverter.converterToUserRole(registerRequestDTO, sysUser, userInfo);
        userRoleService.saveBatch(roles);
        return ResultResponse.returnByEnum(ResultEnum.POJO_UPDATE_SUCCESS);
    }


}

