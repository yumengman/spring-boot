package com.example.demoServiceImpl;

import com.example.demoDao.test2.GoodsMapper;
import com.example.demoEntity.GoodsEntity;
import com.example.demoService.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-26 14:59
 **/
@Slf4j
@Service
@ComponentScan({"com.example.demoDao"})
public class GoodsServiceImpl implements GoodsService {
	@Resource(name="goodsMapper")
	private GoodsMapper goodsMapper;

	@Override
	public List<GoodsEntity> getgoodslist() {
		try {
			return goodsMapper.selectGoodsList();
		}catch (Exception e){
			log.error("GoodsServiceImpl getgoodslist error:",e);
		}
		return null;
	}

	@Override
	public int insertGoods(GoodsEntity goodsEntity) {
		try {
			return goodsMapper.insertGoods(goodsEntity);
		}catch (Exception e){
			log.error("GoodsServiceImpl insertGoods error:",e);
		}
		return -1;
	}
}
