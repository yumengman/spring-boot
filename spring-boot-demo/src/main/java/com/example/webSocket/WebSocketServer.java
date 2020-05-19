package com.example.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demoService.UserService;
import com.example.demoUtil.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author ymm
 * @Date 2018-11-12 17:30
 **/
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{fid}",configurator = SpringConfigurator.class)
public class WebSocketServer {
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UserService userService;

//	private static Map<Integer, WebSocketServer> clients = new ConcurrentHashMap<>(); // 用于存放会话记录
	private Session session;
	private Integer fid;
	private static String Third_Redis_fid = "ThirdRedisFid";
	private RedisUtil redisUtil = new RedisUtil();


	/**
	 * 建立连接，进行会话
	 * @param fid 		销售单ID
	 * @param session  WebSocket 得session
	 * @throws IOException
	 * @author  ymm
	 */
	@OnOpen
	public void onOpen(@PathParam("fid") Integer fid, Session session){
		// 首先从redis中获取已放入的map
		redisUtil.setRedisTemplate(redisTemplate);

		Map<Object,Object> map = redisUtil.hmget(Third_Redis_fid);
		 if (!(map != null && map.size() > 0)){
			 map = new ConcurrentHashMap<>();
		 }
		try {
			this.fid = fid;
			this.session = session;
			map.put(fid+"",this);
			redisUtil.hmset(Third_Redis_fid,map);
//			clients.put(fid, this);
			log.debug("WebSocketServer onOpen 已连接");
		}catch (Exception e){
			log.error("WebSocketServer onOpen error:",e);
		}
	}

	/**
	 * 关闭会话
	 * @throws IOException
	 * @author ymm
	 */
	@OnClose
	public void onClose() {
		try {
//			clients.remove(fid);
			redisUtil.hdel(Third_Redis_fid,fid+"");

		}catch (Exception e){
			log.error("WebSocketServer onClose error:",e);
		}
	}

	@OnMessage
	public void onMessage(String message) throws IOException {

//		JSONObject jsonTo = JSONObject.fromObject(message);
		JSONObject jsonTo = JSON.parseObject(message);
		if (jsonTo.get("To") != null && !jsonTo.get("To").equals("All")){
			sendMessageTo("给一个人", jsonTo.get("To").toString());
		}else{
//			sendMessageAll("给所有人");
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	/**
	 * 发送给指定得用户
	 * @param message  消息内容
	 * @param to		接收人
	 * @throws IOException
	 * @author   ymm
	 */
	public void sendMessageTo(String message, String to) {
		try {
			WebSocketServer item = (WebSocketServer) redisUtil.hget(Third_Redis_fid,to);
			item.session.getAsyncRemote().sendText(message);
		}catch (Exception e){
			log.error("WebSocketServer sendMessageTo error:",e);
		}
	}

//	public void sendMessageAll(String message) throws IOException {
//		for (WebSocketServer item : clients.values()) {
//			item.session.getAsyncRemote().sendText(fid+""+message);
//
//		}
//	}



//	public static synchronized int getOnlineCount() {
//		return onlineCount;
//	}

//	public static synchronized Map<Integer, WebSocketServer> getClients() {
//		return clients;
//	}
}
