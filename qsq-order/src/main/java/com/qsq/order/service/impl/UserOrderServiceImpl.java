package com.qsq.order.service.impl;

import com.qsq.order.po.UserOrder;
import com.qsq.order.mapper.UserOrderMapper;
import com.qsq.order.service.UserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户订单表 服务实现类
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

}
