package com.gupaoedu.vip.mall.goods.feign;

import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.vip.mall.cart.model.Cart;
import com.gupaoedu.vip.mall.goods.model.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*****
 * @Author:
 * @Description:
 ****/
@FeignClient(value = "mall-goods")    //服务名字
public interface SkuFeign {

    /****
     * 根据ID获取Sku
     */
    @GetMapping(value = "/sku/{id}")
    public RespResult<Sku> one(@PathVariable(value = "id") String id);

    /****
     * 根据推广分类查询推广产品列表
     */
    @GetMapping(value = "/sku/aditems/type")
    List<Sku> typeItems(@RequestParam(value = "id") Integer id);

    /****
     * 根据推广分类查询推广产品列表
     *
     */
    @DeleteMapping(value = "/sku/aditems/type")
    RespResult delTypeItems(@RequestParam(value = "id") Integer id);

    /****
     * 根据推广分类查询推广产品列表
     */
    @PutMapping(value = "/sku/aditems/type")
    RespResult updateTypeItems(@RequestParam(value = "id") Integer id);
    /***
     * 库存递减
     * @param carts
     * @return
     */
    @PostMapping(value = "/sku/dcount")
    RespResult decount(List<Cart> carts);
}
