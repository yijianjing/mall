package com.gupaoedu.vip.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*****
 * @Author:
 * @Description:
 ****/
@SpringBootApplication
@MapperScan(basePackages = {"com.gupaoedu.vip.mall.pay.mapper"})
public class MallPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class,args);
    }
}
