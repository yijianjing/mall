package com.gupaoedu.vip.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.vip.mall.goods.model.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/*****
 * @Author: 波波
 * @Description: 云商城
 ****/
public interface SkuMapper extends BaseMapper<Sku> {
    //库存递减
    @Update("UPDATE sku SET num=num-#{num} WHERE id=#{id} AND num>=#{num}")
    int decount(@Param("id") String skuId, @Param("num") Integer num);
}
