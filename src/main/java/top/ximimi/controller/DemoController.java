package top.ximimi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/get-default-text")
    public String getDefaultText(){
        Date date = new Date();

        return date.toString();
    }

}
