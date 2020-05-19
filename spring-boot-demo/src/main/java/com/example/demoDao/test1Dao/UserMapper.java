package com.example.demoDao.test1Dao;

import com.example.demoEntity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
@Mapper
public interface UserMapper {
    List<UserEntity> getUserList(Map<String, Object> map);
}
