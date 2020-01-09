package com.qsq.test.controller;

import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.test.po.SysUser;
import com.qsq.test.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QSQ
 * @create 2019/12/29 14:45
 * No, again
 * 〈 测试控制层 〉
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/success")
    public ResultResponse testCommonModule() {
        throw ExceptionEnum.EXIST_SAME_USERNAME.getException();
    }

    @GetMapping("{id}")
    public ResultResponse info(@PathVariable Integer id) {
        SysUser sysUser = sysUserService.getById(id);
        return ResultResponse.success(sysUser);
    }


}