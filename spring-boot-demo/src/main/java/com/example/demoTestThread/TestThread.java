package com.example.demoTestThread;

import com.example.demoEntity.UserEntity;
import com.example.demoUtil.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3.
 */
public class TestThread implements Runnable {
    private List<UserEntity> userAllList = null;
    private int start = 0;
    public TestThread(List<UserEntity> userList,int start) {
        this.userAllList = userList;
        this.start = start;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (this.getClass()) {
                long start = System.currentTimeMillis();
                List<String> list = new ArrayList<>();
                for (int i=0; i < userAllList.size(); i++){
                    UserEntity userEntity = userAllList.get(i);
                    System.out.println("threadName:"+Thread.currentThread().getName()+"          ID:"+userEntity.getId()+"            Name:"+userEntity.getName()+"       " +
                            "loginName:"+userEntity.getLoginname()+"         password:"+userEntity.getPassword());
                    list.add("threadName:"+Thread.currentThread().getName()+"          ID:"+userEntity.getId()+"            Name:"+userEntity.getName()+"       " +
                            "loginName:"+userEntity.getLoginname()+"         password:"+userEntity.getPassword());
                }
                TestUtil.write(start+"threadName",list);
                System.err.println("单个线程跑"+(System.currentTimeMillis() - start));
                break;




            }
        }
    }
}
