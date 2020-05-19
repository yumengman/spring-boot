package com.example.demoServiceImpl;

import com.example.demoDao.mapper2.HAttRecordMapper;
import com.example.demoEntity.HAttRecord;
import com.example.demoService.HAttRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-12-04 14:06
 **/
@Slf4j
@Service
public class HAttRecordServiceImpl implements HAttRecordService {
	@Autowired
	private HAttRecordMapper hAttRecordMapper;
	@Override
	public List<HAttRecord> getHAttRecordList() {
		try {
			return hAttRecordMapper.getHAttRecordList();
		}catch (Exception e){
			log.error("HAttRecordServiceImpl getHAttRecordList error:",e);
		}
		return null;
	}
}
