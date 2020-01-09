package com.qsq.order.controller;


import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.model.ResultResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.order.po.GoodsDetail;
import com.qsq.order.service.GoodsDetailService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 商品详情 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/goodsDetail")
public class GoodsDetailController extends BaseController<GoodsDetailService,GoodsDetail> {

    @PostMapping("/info")
    @CheckAuth("hasRole('admin')")
    public ResultResponse getGoodsInfo(){
        return ResultResponse.success(service.getById(1));
    }

}

