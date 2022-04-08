package top.ximimi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {

    private Session session;
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        log.info(session.getId());
        sessionSet.add(session);
        log.info("【web socket 消息】，有新的连接，连接的总数为：{}",sessionSet.size());
    }

    @OnClose
    public void onClose(Session session){
        sessionSet.remove(session);
        log.info("【web socket 消息】, 断开连接，连接的总数为：{}",sessionSet.size());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("【web socket 消息】，收到客户端发来的消息：{}",message);
    }

    public void sendMessage(String message){
        for(Session session: sessionSet){
            log.info("【web socket 消息】广播消息，message = {}", message);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToOne(String message){
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
