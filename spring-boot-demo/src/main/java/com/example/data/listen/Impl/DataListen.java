package com.example.data.listen.Impl;

import com.example.data.listen.IDataListen;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by yumen on 2018/12/10.
 */
@Slf4j
public class DataListen implements IDataListen {

    @Override
    public void update(Object event, Object msg) {
        log.info("数据发生了变化");
    }
}
