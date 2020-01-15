package com.qsq.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.dto.SysUserListRequestDTO;
import com.qsq.user.dto.SysUserListResponseDTO;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import com.qsq.common.model.BaseController;

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


    /**
     * 用户列表
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/list")
//    @CheckAuth("hasRole('admin')")
    public ResultResponse list(@RequestBody SysUserListRequestDTO requestDTO) {
        Page<SysUserListResponseDTO> page = super.converterDTOToPageInit(requestDTO);
        return ResultResponse.successPage(page,service.sysUserList(page,requestDTO));
    }


}

