package com.example.demoEntity;

import lombok.Data;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-12-04 13:54
 **/
@Data
public class HAttRecord {
	private Integer uuid;//` int(11) NOT NULL,
	private String worknum;//` varchar(10) DEFAULT NULL
	private String staffname;//` varchar(10) DEFAULT NU
	private String timecardid;//` varchar(20) DEFAULT N
	private String cdatetime;//` datetime DEFAULT NULL,

}
