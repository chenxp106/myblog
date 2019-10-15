package cn.gdut.myblog.system.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemRouterController {

    @GetMapping("/login")
    public String login(){
        return "admin/page/login";
    }

    @GetMapping("/system")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/admin/page/layout")
    public String layout(){
        return "admin/page/layout";
    }

    @GetMapping("/admin/page/index")
    public String index(){
        return "admin/page/index";
    }
}
