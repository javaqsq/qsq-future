package com.qsq.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.collect.Maps;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.jwt.JwtOperator;
import com.qsq.common.model.ResultResponse;
import com.qsq.order.feign.UserModuleFeign;
import com.qsq.order.feign.UserModuleFeignFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author QSQ
 * @create 2020/1/4 11:30
 * No, again
 * 〈〉
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class TestController {

    @Autowired
    private UserModuleFeign userModuleFeign;

    @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("/get/{id}")
    @CheckAuth("hasRole('admin')")
    @SentinelResource(value = "test-sentinel",
//            blockHandler = "block",
//            fallback = "fall"
//            这两个是配置一起使用的
            defaultFallback = "fallback",
            fallbackClass = {UserModuleFeignFallback.class}
    )
    public ResultResponse getOrder(@PathVariable("id") Integer id) {
        log.info("id : {}", id);
        return userModuleFeign.getUserInfo(id);
    }


    public ResultResponse block(Integer id, BlockException e) {
        return ResultResponse.fail(900, "降级");
    }
//
//    public ResultResponse fall(Integer id,Throwable e) {
//        if (e instanceof BusinessRuntimeException){
//            log.error("出现",((BusinessRuntimeException) e).getErrorMsg());
//            return ResultResponse.fail(800, ((BusinessRuntimeException) e).getErrorMsg());
//        }
//        return ResultResponse.fail(800, "熔断");
//    }


}