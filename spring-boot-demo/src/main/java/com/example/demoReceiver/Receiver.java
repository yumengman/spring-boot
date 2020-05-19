package com.example.demoReceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description     消息接收者
 * @Date 2019-02-25 12:18
 * @Author yumengman
 **/
@Slf4j
@Component
@RabbitListener(queues = "lyhTest1")
public class Receiver {
    @RabbitHandler
    public void receiver(String msg){
        log.info("Test1 receiver1:"+msg);
    }

}