package com.qsq.order.feign;

import com.qsq.common.model.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author QSQ
 * @create 2020/1/4 14:40
 * No, again
 * 〈〉
 */
@FeignClient(name = "user-module" )
@Component
public interface UserModuleFeign {

    @GetMapping("/user/get/{id}")
    ResultResponse getUserInfo(@PathVariable("id") Integer id);

}
