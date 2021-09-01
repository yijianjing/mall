package com.gupaoedu.vip.mall.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.cart.feign.CartFeign;
import com.gupaoedu.vip.mall.cart.model.Cart;
import com.gupaoedu.vip.mall.goods.feign.SkuFeign;
import com.gupaoedu.vip.mall.order.mapper.OrderMapper;
import com.gupaoedu.vip.mall.order.mapper.OrderRefundMapper;
import com.gupaoedu.vip.mall.order.mapper.OrderSkuMapper;
import com.gupaoedu.vip.mall.order.model.Order;
import com.gupaoedu.vip.mall.order.model.OrderRefund;
import com.gupaoedu.vip.mall.order.model.OrderSku;
import com.gupaoedu.vip.mall.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/*****
 * @Author:
 * @Description:
 ****/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartFeign cartFeign;

    /***
     * 添加订单
     * 作业：
     *      1)价格如何校验？
     */
    @GlobalTransactional
    @Override
    public Boolean add(Order order) {
        //1.查询购物车记录
        RespResult<List<Cart>> cartResp = cartFeign.list(order.getCartIds());
        List<Cart> carts = IterableConverter.toList(cartResp.getData());
        if(carts.size()==0){
            return false;
        }
        //2.库存递减   20000  成功
        skuFeign.decount(carts);

        //3.增加订单明细
        int totlNum = 0;    //商品个数
        int payMoney = 0;   //支付总金额
        for (Cart cart : carts) {
            //类型转换
            OrderSku orderSku = JSON.parseObject(JSON.toJSONString(cart), OrderSku.class);
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setMoney(orderSku.getPrice()*orderSku.getNum());
            orderSku.setSkuId(cart.getSkuId());
            orderSku.setOrderId(order.getId());
            orderSkuMapper.insert(orderSku);

            //统计数据
            totlNum+=cart.getNum();
            payMoney+=orderSku.getMoney();
        }
        //4.增加订单
        order.setTotalNum(totlNum);
        order.setMoneys(payMoney);
        orderMapper.insert(order);

        //5.删除购物车记录
        cartFeign.delete(order.getCartIds());
        return true;
    }
}