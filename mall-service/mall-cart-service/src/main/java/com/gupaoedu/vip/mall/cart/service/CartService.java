package com.gupaoedu.vip.mall.cart.service;

import com.gupaoedu.vip.mall.cart.model.Cart;

import java.util.List;

public interface CartService {
    //删除购物车集合
    void delete(List<String> ids);

    /**
     * 加入购物车
     * @param id :skuid
     * @param userName:用户名
     * @param num ： 加入购物车数量
     */
    void add(String id,String userName,Integer num);
    /***
     * 购物车列表
     * @param userName
     * @return
     */
    List<Cart> list(String userName);
    /***
     * 根据ID集合查询购物车列表
     * @param ids
     * @return
     */
    Iterable<Cart> list(List<String> ids);

}