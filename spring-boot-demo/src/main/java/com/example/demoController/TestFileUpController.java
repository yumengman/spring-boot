package com.example.demoController;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Date 2019-11-26 09:42
 * @Author ymm
 **/

@RestController
@RequestMapping("/api/up")
@Api("上传附件")
public class TestFileUpController {

    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    private String aliyunBucketName = "oss-ymm";
    private String aliyunAccessKeyId = "LTAI4FvTkkGbTTjnwD2YVZH3";
    private String aliyunAccessKeySecret = "JN9zhPOjvNY8IuJ8AvliuNcHmwxSAh";

    @PostMapping("/upload")
    @ApiOperation(value = "上传附件")
    public String getReportByOrgAndMonth(MultipartFile file) throws Exception{
        String fileName = UUID.randomUUID().toString();
        String suxx = null;
        int index = file.getOriginalFilename().lastIndexOf(".");
        suxx = file.getOriginalFilename().substring(index);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, aliyunAccessKeyId, aliyunAccessKeySecret);
        ossClient.putObject(aliyunBucketName, fileName + suxx, file.getInputStream());
        // 关闭OSSClient。
        ossClient.shutdown();
        return fileName + suxx;
    }

    @GetMapping("/getPath")
    @ApiOperation(value = "获取文件访问地址")
    public URL getPath(String path, HttpServletResponse response) throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, aliyunAccessKeyId, aliyunAccessKeySecret);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(aliyunBucketName, path, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }
}