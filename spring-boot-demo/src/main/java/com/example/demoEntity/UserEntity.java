package com.example.demoEntity;

import lombok.Data;

/**
 * Created by Administrator on 2018/8/31.
 */
@Data
public class UserEntity {
    private int id;
    private String loginname;
    private String password; // 加密 
    private String name;
}
