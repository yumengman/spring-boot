package com.example.demoController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @Description     测试rabbitMQ消息
 * @Date 2019-02-25 12:15
 * @Author yumengman
 **/
@Slf4j
@RestController
@RequestMapping("/testRabbitMQ")
public class TestRabbitMsg {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping("/send")
    public void send(String text){
        if (StringUtils.isEmpty(text)){
            for (int i = 0; i < 10000; i++) {
                amqpTemplate.convertAndSend("lyhTest1", UUID.randomUUID().toString());
            }
        }else {
            amqpTemplate.convertAndSend("lyhTest1",text);
        }
    }
}