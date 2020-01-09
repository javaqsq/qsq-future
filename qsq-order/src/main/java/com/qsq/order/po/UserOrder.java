package com.qsq.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qsq.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户订单表
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserOrder extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private Long payment;

    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private Integer paymentType;

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     */
    private Integer status;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家是否已经评价
     */
    private Integer buyerRate;


}
