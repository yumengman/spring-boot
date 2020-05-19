package com.example.demoController;

import com.example.demoEntity.GoodsEntity;
import com.example.demoEntity.UserEntity;
import com.example.demoTestThread.TestThread;
import com.example.demoUtil.RedisUtil;
import com.example.webSocket.WsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
/**
 * Created by Administrator on 2018/9/3.
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestTheadController {
    @Autowired
    private RedisTemplate<Object, Object> template;

    @RequestMapping("/testThread")
    @ResponseBody
    public Map<String,Object> user(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Hashtable<String,Object> hashtable = new Hashtable<>();
        hashtable.put("1","11");
        List<UserEntity> userList = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i< 10000; i++){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i+1);
            userEntity.setLoginname("test"+(i+1));
            userEntity.setPassword(random.nextLong() + "");
            userList.add(userEntity);
        }

        // 单线程：
//        TestThread testThread1 = new TestThread(userList);
//        Thread thread2 = new Thread(testThread1,"A");
//        thread2.start();
        // 多线程进行分线程处理
        int count = userList.size(); // 总条数
        int size = 500;//处理条数
        int num = count%size==0?count/size:(count/size+1);//算出几个线程
        CountDownLatch latch = new CountDownLatch(num);
//        TestThread[] threads = new TestThread[num];
        for(int i=0;i<num;i++){
            int start = i*size;//从什么地方开始
            List<UserEntity> getlist = null;
            if (i != (num-1)){
                getlist = getLimitList(userList,start,start+size);
            } else{
                getlist = getLimitList(userList,start,userList.size()-1);
            }
            TestThread testThread = new TestThread(getlist,start);
            testThread.run();
//            threads[i].run();
        }

//        int num = userList.size();
//        int thread = 2;// 线程数
//        final int avg = num / thread;// 每个线程读取的邮件数量
//        List<Thread> listThreads = new ArrayList<>();
//        for (int i = 0; i < thread; i++) {
//            // 3个线程一起工作
//            final int count = i;
//            listThreads.add(new Thread() {
//                @Override
//                public void run() {
//                        synchronized (this.getClass()){
//                            long start = System.currentTimeMillis();
//                            List<String> users = new ArrayList<>();
//                            for (int j = 0; j < avg; j++) {
//                                int number = j + count * avg;// 邮件的编号
//                                UserEntity userEntity = userList.get(number);
////                                System.out.println("threadName:"+Thread.currentThread().getName()+"          ID:"+userEntity.getId()+"            Name:"+userEntity.getName()+"       " +
////                                        "loginName:"+userEntity.getLoginname()+"         password:"+userEntity.getPassword());
//                                users.add("threadName:"+Thread.currentThread().getName()+"          ID:"+userEntity.getId()+"            Name:"+userEntity.getName()+"       " +
//                                        "loginName:"+userEntity.getLoginname()+"         password:"+userEntity.getPassword());
//                            }
//                            TestUtil.write(count+"threadName",users);
//                            System.err.println("10个线程跑"+(System.currentTimeMillis() - start));
//                        }
//                }
//            });
//        }
//        for (int i = 0; i < thread; i++) {
//            listThreads.get(i).start();
//        }
        return map;
    }
    public List<UserEntity> getLimitList(List<UserEntity> getList,int start, int end){
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = start; i < end; i++){
            userEntities.add(getList.get(i));
        }
        return userEntities;
    }

    @RequestMapping("/testRedis")
    @ResponseBody
    public Map<String,Object> testRedis(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Random random = new Random();
        try {
            // 放入string数据
            template.opsForValue().set("ymm","测试ymm"+random.nextLong()+"");
            RedisUtil redisUtil = new RedisUtil();
            redisUtil.setRedisTemplate(template);
            redisUtil.set("ymmm","测试ymmm"+random.nextLong());
            redisUtil.set("ymmmm","测试ymmmm"+UUID.randomUUID());
            // 获取数据
            System.out.println("ymm的值："+redisUtil.get("ymm").toString());
            System.out.println("ymmm的值："+redisUtil.get("ymmm").toString());
            System.out.println("ymmmm的值："+redisUtil.get("ymmmm"));
            // 放入list
            List<String> list = new ArrayList<>();
            list.add(UUID.randomUUID()+"");
            list.add(UUID.randomUUID()+"");
            list.add(UUID.randomUUID()+"");
            list.add(UUID.randomUUID()+"");
            list.add(UUID.randomUUID()+"");
            redisUtil.lSet("ymmlist",list);
            list.clear();
            System.out.println("ymmlist："+redisUtil.lGet("ymmlist",0,-1));
            System.out.println("ymmlist："+redisUtil.lGetListSize("ymmlist"));


            List<Object> linkedList = new LinkedList<>();
            linkedList.add(UUID.randomUUID()+"");
            linkedList.add(UUID.randomUUID()+"");
            linkedList.add(UUID.randomUUID()+"");
            linkedList.add(UUID.randomUUID()+"");
            linkedList.add(UUID.randomUUID()+"");
            redisUtil.lSet("linkedList",linkedList);
            linkedList.clear();
            linkedList = redisUtil.lGet("linkedList",0,-1);
            System.out.println("linkedList："+linkedList);
            System.out.println("linkedList："+redisUtil.lGetListSize("linkedList"));


            Vector<Object> vector = new Vector<>();
            vector.add(UUID.randomUUID()+"");
            vector.add(UUID.randomUUID()+"");
            vector.add(UUID.randomUUID()+"");
            vector.add(UUID.randomUUID()+"");
            vector.add(UUID.randomUUID()+"");
            redisUtil.lSet("vector",vector);
            System.out.println("vector："+redisUtil.lGet("vector",0,-1));
            System.out.println("vector："+redisUtil.lGetListSize("vector"));


            // 散列类型hash hashset
            HashSet<Object> hashSet = new HashSet<>();
            hashSet.add(UUID.randomUUID()+"");
            hashSet.add(UUID.randomUUID()+"");
            hashSet.add(UUID.randomUUID()+"");
            hashSet.add(UUID.randomUUID()+"");
            hashSet.add(UUID.randomUUID()+"");
            redisUtil.hset("hashSet","3",hashSet);
            hashSet.clear();
            hashSet.add(UUID.randomUUID()+"");
            hashSet.add(UUID.randomUUID()+"");
            redisUtil.hset("hashSet","4",hashSet);
            hashSet.clear();
            System.out.println("hashSet："+redisUtil.hget("hashSet","3"));
            System.out.println("hashSet："+redisUtil.hget("hashSet","4"));
            System.out.println("hlen："+redisUtil.hlen("hashSet"));


            GoodsEntity goodsEntity = new GoodsEntity();
            Map<Object,Object> currmap = new ConcurrentHashMap<>();
            currmap.put("12000",goodsEntity);
            redisUtil.hmset("Third_Redis_fid",currmap);
            Map<Object,Object> currmap2 = redisUtil.hmget("Third_Redis_fid");
            log.info("currmap2" + currmap2);


        }catch (Exception e){
            log.error("TestTheadController testRedis error:",e);
        }
        return map;
    }

    @RequestMapping("/testWebSocketRedis")
    @ResponseBody
    public Map<String ,Object> testWebSocket(){
        WsHandler wsHandler = new WsHandler();
//        wsHandler.handleTextMessage();
        return null;
    }
}
