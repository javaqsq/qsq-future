package com.qsq.qsqblog.controller;

import com.qsq.common.model.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QSQ
 * @create 2020/1/28 16:16
 * No, again
 * 〈〉
 */
@RequestMapping("/blog/test")
@RestController
public class TestController {

    @RequestMapping("/")
    public ResultResponse test() {
        return ResultResponse.success();
    }

}