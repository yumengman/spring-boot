package com.example;
import com.example.demoUtil.FaceUtil;
import com.example.demoUtil.GsonUtils;
import com.example.demoUtil.HttpUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description     百度人脸识别
 * @Date 2020-02-25 18:16
 * @Author ymm
 **/
public class FaceMatchTest {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceMatch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            Map map = new HashMap<>();
            String image1 = FaceUtil.imageToBase64("F:\\百天照\\宋涵- 8x8寸雅菲\\_MG_9482.JPG");
            map.put("image",image1);
            map.put("image_type","BASE64");
            map.put("face_type","LIVE");
            map.put("quality_control","LOW");
            map.put("liveness_control","NONE");
            List list = new ArrayList();
            list.add(map);
            Map map2 = new HashMap<>();
            String image2 = FaceUtil.imageToBase64("F:\\微信图片_20200226145718.jpg");
            map2.put("image",image2);
            map2.put("image_type","BASE64");
            map2.put("face_type","LIVE");
            map2.put("quality_control","LOW");
            map2.put("liveness_control","NONE");
            list.add(map2);
            String param = GsonUtils.toJson(list);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = FaceUtil.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        faceMatch();
    }
}