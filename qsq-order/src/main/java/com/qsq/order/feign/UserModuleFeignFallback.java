package com.qsq.order.feign;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qsq.common.exception.BusinessRuntimeException;
import com.qsq.common.model.ResultResponse;
import lombok.extern.slf4j.Slf4j;


/**
 * @author QSQ
 * @create 2020/1/5 22:45
 * No, again
 * 〈〉
 */
@Slf4j
public class UserModuleFeignFallback {

    public static ResultResponse fallback(Throwable e) {
        if (e instanceof BusinessRuntimeException) {
            log.error("出现", ((BusinessRuntimeException) e).getErrorMsg());
            return ResultResponse.fail(((BusinessRuntimeException) e).getErrorCode(), ((BusinessRuntimeException) e).getErrorMsg());
        }
        return ResultResponse.fail(800, "服务出现熔断");
    }


    public static ResultResponse globalBlockHandler(BlockException ex) {
        System.out.println("Oops: " + ex.getClass().getSimpleName());
        return  ResultResponse.fail(800, "服务出现熔断");
    }






}