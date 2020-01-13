package com.qsq.test.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.common.uitl.RandomImageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * @author QSQ
 * @create 2019/12/29 21:19
 * No, again
 * 〈〉
 */
@RestController
@RequestMapping("/test")
public class CommonController {

    @GetMapping("/code")
    public ResultResponse code(HttpServletResponse response) {
        return ResultResponse.success();
    }


    @GetMapping("/image/code")
    public void getRandomCode(HttpServletResponse response) throws IOException {
        RandomImageGenerator.getRandomCode(response);
    }


}