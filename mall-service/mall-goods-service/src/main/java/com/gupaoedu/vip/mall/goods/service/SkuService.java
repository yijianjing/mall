package com.gupaoedu.vip.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.vip.mall.goods.model.Sku;

import java.util.List;

public interface SkuService extends IService<Sku> {

    /***
     * 根据推广产品分类ID查询Sku列表
     * @param id
     * @return
     */
    List<Sku> typeSkuItems(Integer id);
    /***
     * 清理分类ID下的推广产品
     * @param id
     */
    void delTypeSkuItems(Integer id);

}