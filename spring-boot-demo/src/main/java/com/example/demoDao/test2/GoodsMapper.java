package com.example.demoDao.test2;

import com.example.demoEntity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-23 13:58
 **/
@Mapper
public interface GoodsMapper {
	List<GoodsEntity> selectGoodsList();

	int insertGoods(GoodsEntity goodsEntity);
}
