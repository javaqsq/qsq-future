package com.qsq.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.order.po.UserOrder;
import com.qsq.order.service.UserOrderService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 用户订单表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/userOrder")
public class UserOrderController extends BaseController<UserOrderService,UserOrder> {

}

