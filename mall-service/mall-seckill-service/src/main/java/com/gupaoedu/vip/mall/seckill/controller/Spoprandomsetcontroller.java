package com.gupaoedu.vip.mall.seckill.controller;

import com.gupaoedu.vip.mall.seckill.service.impl.Spoprandomsetservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Spoprandomsetcontroller {
 
    @Autowired
    private Spoprandomsetservice spoprandomsetservice;

    @GetMapping("/random/user")
    public String start() {
        return "幸运用户:"+spoprandomsetservice.start();
    }
 
}