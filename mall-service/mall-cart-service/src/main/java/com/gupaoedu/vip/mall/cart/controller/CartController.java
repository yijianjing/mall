package com.gupaoedu.vip.mall.cart.controller;

import com.google.common.collect.Lists;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.cart.model.Cart;
import com.gupaoedu.vip.mall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    /***
     * 添加购物车
     * id: skuid
     * http://localhost:8087/cart/1318596430360813570/1
     *
     */
    @GetMapping(value = "/{id}/{num}")
    public RespResult add(@PathVariable(value = "id")String id, @PathVariable(value = "num")Integer num){
        //用户名字
        String userName="gp";
        //加入购物车
        cartService.add(id,userName,num);
        return RespResult.ok();
    }

    /****
     * 购物车列表
     * @return
     */
    @GetMapping(value = "/list")
    public RespResult<List<Cart>> list(){
        String userName = "gp";
        List<Cart> carts = cartService.list(userName);
        return RespResult.ok(carts);
    }

    /***
     * 购物车数据
     */
    @PostMapping(value = "/list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids){
        //购物车集合
        List<Cart> carts = Lists.newArrayList(cartService.list(ids));
//        String userName = "gp";
//        List<Cart> carts = cartService.list(userName);

        return RespResult.ok(carts);
    }

    /***
     * 删除指定购物车
     */
    @DeleteMapping
    public RespResult delete(@RequestBody List<String> ids){
        //删除购物车集合
        cartService.delete(ids);
        return RespResult.ok();
    }

}