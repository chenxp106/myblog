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

    @GetMapping("/admin/page/tag")
    public String tag(){
        return "admin/page/tag/index";
    }

    @GetMapping("/admin/page/tag/edit")
    public String tarEdit(){
        return "admin/page/tag/form";
    }

    @GetMapping("/admin/page/link")
    public String link(){
        return "admin/page/link/index";
    }

    @GetMapping("/admin/page/link/edit")
    public String linkEdit(){
        return "admin/page/link/form";
    }


    @GetMapping("/admin/page/category")
    public String category(){
        return "admin/page/category/index";
    }

    @GetMapping("/admin/page/category/edit")
    public String categoryEdit(){
        return "admin/page/category/form";
    }


}
