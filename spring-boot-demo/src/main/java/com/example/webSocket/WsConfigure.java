package com.example.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-23 16:21
 **/
@Slf4j
@Configuration
@EnableWebSocket
public class WsConfigure implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		log.info("==========================");
		registry.addHandler(myHandler(), "/websockets/{fid}").setAllowedOrigins("*");
	}
	@Bean
	public WsHandler myHandler()
	{
		return new WsHandler();
	}
}
