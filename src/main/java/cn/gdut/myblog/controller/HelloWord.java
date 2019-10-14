package cn.gdut.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWord {

    @ResponseBody
    @GetMapping("/")
    public String hello(){
        return "hello word";
    }
}
