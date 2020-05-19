package com.example.demoController;

import com.example.demoEntity.HAttRecord;
import com.example.demoService.HAttRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-12-04 14:01
 **/
@Slf4j
@RestController
@RequestMapping("hattrecord")
public class HAttRecordController {
	@Autowired
	private HAttRecordService hAttRecordService;


	@RequestMapping("/getlist")
	public List<HAttRecord> getlist(){
		return hAttRecordService.getHAttRecordList();
	}

}
