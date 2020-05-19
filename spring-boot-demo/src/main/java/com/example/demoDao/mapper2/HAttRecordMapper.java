package com.example.demoDao.mapper2;


import com.example.demoEntity.HAttRecord;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
@Mapper
public interface HAttRecordMapper {
    List<HAttRecord> getHAttRecordList();
}
