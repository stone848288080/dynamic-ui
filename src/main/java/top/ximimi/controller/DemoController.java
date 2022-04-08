package top.ximimi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return date.toString();
    }


    @GetMapping("/get-websocket-text/{username}")
    public void getWebSocketMessage(@PathVariable("username") String userName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < 10 ; i++) {
            String date = sdf.format(new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webSocket.sendMessageToOne(date,userName);
        }
    }
}
