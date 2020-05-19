package com.example.demoController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description         测试HashMap、Hashtable、ConcurrentHashMap
 * @Date 2019-02-24 13:15
 * @Author yumengman
 **/
@Slf4j
@RestController
@RequestMapping("/testMap")
public class TestMapController {

    /**
     * @Description    setmap值
     * @Date 2019-02-24 13:19
     * @Param []
     * @Author yumengman
     */
    @RequestMapping("/setMap.json")
    public void setMap(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("","");
    }

    /**
     * @Description    获取map中所有的数据。
     * @Date 2019-02-24 13:20
     * @Param []
     * @Author yumengman
     */
    @RequestMapping("/getMap.json")
    public void getMap(){

    }

    /**
     * @Description    分段读取map中的数据
     * @Date 2019-02-24 13:20
     * @Param []
     * @Author yumengman
     */
    @RequestMapping("/readMap.json")
    public void readMap(){

    }
}