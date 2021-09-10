package com.gupaoedu.vip.mall.seckill.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Spoprandomsetservice {

    @Autowired
    private RedisTemplate redisTemplate;
 
    private static final String spop_user_sets = "pop:user:set";
 
    // 把所有员工全部添加到集合列表中
    @PostConstruct
    public void initdata(){
        log.info("初始化奖品等级信息...");
        // 判断集合是否已经存在
        boolean flag = this.redisTemplate.hasKey(spop_user_sets);
        // 防止作弊
        if (!flag) {
            // 获取所有员工的信息
            List<Integer> initdatalist = initdatalist();
            // 把员工信息写入到redis中 sadd key data
            initdatalist.forEach(data -> this.redisTemplate.opsForSet().add(spop_user_sets, data));
        }
    }
    // 模拟100用户抽奖
    private List<Integer> initdatalist() {
        // todo : 从数据库里面来，把公司里面所有的员工从数据表中全部查询出来
        List<Integer> listdata = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listdata.add(i + 1);
        }
        return listdata;
    }
    // 随机抽取用户
    public int start(){
        return (int)redisTemplate.opsForSet().pop(spop_user_sets);
    }
}