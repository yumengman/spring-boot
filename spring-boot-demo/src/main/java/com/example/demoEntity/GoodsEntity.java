package com.example.demoEntity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-23 13:59
 **/
@Data
public class GoodsEntity implements Serializable {
	private int id;
	private String goodsName;
	private String price;
}
