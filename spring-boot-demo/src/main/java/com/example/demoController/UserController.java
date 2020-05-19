package com.example.demoController;

import com.example.demoController.param.GoodsEntityParam;
import com.example.demoEntity.GoodsEntity;
import com.example.demoService.GoodsService;
import com.example.demoService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/28.
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private GoodsService goodsService;


    @RequestMapping("/user/userpage")
    public ModelAndView userpage(){
        return new ModelAndView("user");
    }

    @RequestMapping("/user/user")
    public Map<String,Object> user(HttpServletRequest request){
        String loginId = request.getParameter("login_id");
        String loginname = request.getParameter("loginname");
        Map<String,Object> map = new HashMap<>();
        if (loginId != null && !"".equals(loginId))
            map.put("id",loginId);
        if (loginname != null && !"".equals(loginname))
            map.put("loginname",loginname);
        map = userService.getUserList(map);
//        log.error("iiii"+loginId+"22222");
        return map;
    }
    @RequestMapping("/user/getGoodsList")
    public Map<String,Object> getGoodsList(){
        Map<String,Object> map = new HashMap<>();
        List<GoodsEntity> goodsEntityList = goodsService.getgoodslist();
        map.put("goodsList",goodsEntityList);
        return map;
    }
    @RequestMapping("/user/insertGoods")
    public Map<String,Object> insertGoods(GoodsEntityParam goodsEntityParam){
        Map<String,Object> map = new HashMap<>();
        GoodsEntity goodsEntity = new GoodsEntity();
        BeanUtils.copyProperties(goodsEntityParam,goodsEntity);
        int count = goodsService.insertGoods(goodsEntity);
        map.put("goodsList",count);
        return map;
    }


    /**
     * @Description        批量文件上传
     * @Date 2019-05-30 15:10
     * @Param [request]
     * @Author ymm
     */
    @RequestMapping("fileUpload")
    public Map<String,Object> fileUploads(HttpServletRequest request,@RequestParam("file") MultipartFile[] multFiles){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("message","成功");
        return map;
    }
}
