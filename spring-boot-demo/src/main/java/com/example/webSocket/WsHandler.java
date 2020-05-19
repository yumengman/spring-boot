package com.example.webSocket;

import com.example.demoService.GoodsService;
import com.example.demoUtil.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-23 16:18
 **/
@Slf4j
@Component
public class WsHandler extends TextWebSocketHandler implements Serializable {
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	private static String Third_Redis_fid = "ThirdRedisFid";
	private RedisUtil redisUtil = new RedisUtil();


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		super.afterConnectionClosed(session, status);
		log.info("close....");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		redisUtil.setRedisTemplate(redisTemplate);
		Map<Object,Object> map = redisUtil.hmget(Third_Redis_fid);
		if (!(map != null && map.size() > 0)){
			map = new LinkedHashMap<>();
		}
		String path = session.getUri().getPath();
		int lastIndex = path.lastIndexOf("/");
		String fid = path.substring(lastIndex+1,path.length()-1);
		map.put(fid,session);
		redisUtil.hmset(Third_Redis_fid,map);
		super.afterConnectionEstablished(session);
		log.info("建立新的会话");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
	{
		System.out.println(message.getPayload());
		TextMessage msg=new TextMessage(message.getPayload());
		session.sendMessage(msg);
		log.info("发送消息"+message.getPayload());
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
	{
		super.handleMessage(session, message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
	{
		super.handleTransportError(session, exception);
	}
}
