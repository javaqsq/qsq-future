package com.qsq.test.controller;

import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.common.uitl.RandomImageGenerator;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author QSQ
 * @create 2019/12/29 21:19
 * No, again
 * 〈〉
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/code")
    public ResultResponse code(@RequestParam(value = "username") String username) {
        return ResultResponse.success(username + "5555");
    }


    @GetMapping("/image/code")
    public void getRandomCode(HttpServletResponse response) throws IOException {
        RandomImageGenerator.getRandomCode(response);
    }


}