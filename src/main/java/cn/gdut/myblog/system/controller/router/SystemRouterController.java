package cn.gdut.myblog.system.controller.router;

import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.service.ArticleService;
import cn.gdut.myblog.system.service.CategoryService;
import cn.gdut.myblog.system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SystemRouterController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

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

    // tag相关
    @GetMapping("/admin/page/tag")
    public String tag(){
        return "admin/page/tag/index";
    }

    @GetMapping("/admin/page/tag/edit")
    public String tarEdit(){
        return "admin/page/tag/form";
    }

    // link相关
    @GetMapping("/admin/page/link")
    public String link(){
        return "admin/page/link/index";
    }

    @GetMapping("/admin/page/link/edit")
    public String linkEdit(){
        return "admin/page/link/form";
    }

    // category相关
    @GetMapping("/admin/page/category")
    public String category(){
        return "admin/page/category/index";
    }

    @GetMapping("/admin/page/category/edit")
    public String categoryEdit(){
        return "admin/page/category/form";
    }

    // 文章相关
    @GetMapping("/admin/page/article")
    public String article(){
        return "admin/page/article/index";
    }

    @GetMapping("/admin/page/article/add")
    public String articleAdd(){
        return "admin/page/article/form";
    }

    @GetMapping("/admin/page//article/edit/{id}")
    public String articleEdit(@PathVariable Long id, Model model){
        SysArticle article = articleService.findById(id);
        // 设置tags
        article.setTags(tagService.findByArticleId(article.getId()));
        model.addAttribute("p",article);
        return "admin/page/article/edit";
    }

    // 评论相关
    @GetMapping("/admin/page/comment")
    public String comment(){
        return "admin/page/comment/index";
    }


    // 日志相关
    @GetMapping("/admin/page/log")
    public String log(){
        return "admin/page/log/index";
    }

    // 用户相关
    @GetMapping("/admin/page/user/profile")
    public String profile(){
        return "admin/page/user/profile";
    }

    @GetMapping("/admin/page/user/edit")
    public String userEdit(){
        return "admin/page/user/edit";
    }

}
