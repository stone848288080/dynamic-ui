package top.ximimi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ximimi.websocket.WebSocket;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    WebSocket webSocket;

    @GetMapping("/get-default-text")
    public String getDefaultText(){
        Date date = new Date();
        webSocket.sendMessage(date.toString());
        return date.toString();
    }


    @GetMapping("/get-websocket-text")
    public void getWebSocketMessage(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while(true){
            String date = sdf.format(new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webSocket.sendMessage(date);
        }
    }
}
