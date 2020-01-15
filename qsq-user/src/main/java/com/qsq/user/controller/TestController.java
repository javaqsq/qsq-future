package com.qsq.user.controller;

import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.common.uitl.CharacterGeneratorUtil;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author QSQ
 * @create 2020/1/4 11:30
 * No, again
 * 〈〉
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @CheckAuth("hasLogin()")
    @GetMapping("/get/{id}")
    public ResultResponse getUser(@PathVariable("id") Integer id) {
        log.info("id : {}", id);
        SysUser sysUser = sysUserService.getById(id);
        if (id == 1) {
            throw ExceptionEnum.LOGIN_USER_NOT_EXIST.getException();
        }
        return ResultResponse.success(sysUser);
    }


    /**
     * 造数据
     *
     * @param args
     */
    public static void main(String[] args) {
        String sql = "INSERT INTO `sys_user` VALUES (NULL, '%s', '123456', '%s'," +
                "'http://localhost:8888/file/static/avatar/20200115151551dota2剑圣.jpg'," +
                "'%s', '2019-12-17 16:32:52', '1113948520@qq.com', 1,0, " +
                "'厦门', CURRENT_TIMESTAMP , 0, NULL, NULL, 1, 0);\n";
        ;
        System.out.println(CharacterGeneratorUtil.getRandomJianHan(2));
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(sql, CharacterGeneratorUtil.getStringRandom(10, "char"),
                    CharacterGeneratorUtil.getRandomJianHan(2), "130" + CharacterGeneratorUtil.getStringRandom(8, "num")));
        }

    }


}