package com.gupaoedu.vip.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.vip.mall.order.model.Order;
import com.gupaoedu.vip.mall.order.model.OrderRefund;

/*****
 * @Author:
 * @Description:
 ****/
public interface OrderService extends IService<Order> {

    //添加订单
    Boolean add(Order order) throws Exception;
}
