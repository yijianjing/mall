package com.gupaoedu.vip.mall.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gupaoedu.mall.util.RespCode;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.order.model.Order;
import com.gupaoedu.vip.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*****
 * @Author:
 * @Description:
 ****/
@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;


    /***
     * 添加订单
     *http://localhost:8089/order/add
     *    {
     *     "payType":"1",
     *     "username":"testname",
     *     "recipients":"testname",
     *     "recipientsMobile":139,
     *     "recipientsAddress":"testaddress",
     *     "weixinTransactionId":1,
     *     "totalNum":15,
     *     "moneys":5,
     *     "orderStatus":1,
     *     "payStatus":1,
     *     "cartIds":[1318596430360813570],
     *     "payTime":"2012-01-01",
     *      "consignTime":"2021-01-01",
     *       "endTime":"2021-01-01"
     *
     *          }
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody Order order, HttpServletRequest request) throws Exception {
        String userName = "gp";
        order.setUsername(userName);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setId(IdWorker.getIdStr());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setIsDelete(0);
        List<String> list=new ArrayList<>();
        list.add("gp1318596430360813570");
        order.setCartIds(list);

        //添加订单
        Boolean bo = orderService.add(order);
        return bo? RespResult.ok():RespResult.error(RespCode.SYSTEM_ERROR);
    }


}
