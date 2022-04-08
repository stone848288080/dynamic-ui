package top.ximimi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}")
@Slf4j
public class WebSocket {
    private static Map<String,Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String userName){
        sessionMap.put(userName,session);
        log.info("【web socket 消息】 new user join the websocket connection: {}",userName);
        log.info("【web socket 消息】，有新的连接，连接的总数为：{}", sessionMap.size());
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String userName){
        sessionMap.remove(userName);
        log.info("【web socket 消息】,有一连接关闭，移除username={}的用户session, 当前在线人数为：{}",userName, sessionMap.size());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("【web socket 消息】，收到客户端发来的消息：{}",message);
    }

    public void sendMessageToAll(String message){
        for(Session session: sessionMap.values()){
            log.info("【web socket 消息】广播消息，message = {}", message);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【web socket 消息】 服务端发送消息给客户端失败", e);
            }
        }
    }

    public void sendMessageToOne(String message, String toUser){
        try {
            log.info("服务端给客户端[{}]发送消息:{}", toUser, message);
            sessionMap.get(toUser).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
