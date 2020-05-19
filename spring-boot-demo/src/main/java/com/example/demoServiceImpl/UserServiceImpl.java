package com.example.demoServiceImpl;
import com.example.demoDao.test1Dao.UserMapper;
import com.example.demoEntity.UserEntity;
import com.example.demoService.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
@ComponentScan({"com.example.demoDao"})
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Map<String, Object> getUserList(Map<String, Object> map) {
        try {
            List<UserEntity> listUser = userMapper.getUserList(map);
            map.clear();
            map.put("returnCode",0);
            map.put("returnMsg",listUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
