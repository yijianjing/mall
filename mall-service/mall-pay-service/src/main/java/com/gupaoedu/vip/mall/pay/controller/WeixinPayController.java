package com.gupaoedu.vip.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.pay.model.PayLog;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*****
 * @Author:
 * @Description:
 ****/
@RestController
@RequestMapping(value = "/wx")
@CrossOrigin
public class WeixinPayController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /****
     * 支付结果回调
     */
    @GetMapping(value = "/result")
    public RespResult result(){
        //创建日志对象
        PayLog payLog = new PayLog(IdWorker.getIdStr(),1,"hello!","AAA",new Date());

        //构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        //发消息
        rocketMQTemplate.sendMessageInTransaction("rocket","log",message,null);
        return RespResult.ok();
    }
}
