package com.example.demoService;

import com.example.demoEntity.GoodsEntity;

import java.util.List;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-26 14:58
 **/
public interface GoodsService {
	List<GoodsEntity> getgoodslist();

	int insertGoods(GoodsEntity goodsEntity);
}
