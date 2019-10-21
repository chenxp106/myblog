package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.service.ArticleService;
import cn.gdut.myblog.system.service.ArticleTagService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTagService articleTagService;

    @GetMapping("/list")
    public R<Map<String, Object>> findByPage(SysArticle article, int page, int limit){

        IPage<SysArticle> page1 = null;
        Map<String, Object> data = new HashMap<>();
        page1 = articleService.findByPage(article, page, limit);
        data.put("rows", page1.getRecords());
        data.put("total", page1.getTotal());
        //
        return new R(data);
    }

    @PostMapping
    public R save(@RequestBody SysArticle sysArticle){
        System.out.println(sysArticle);
        articleService.add(sysArticle);
        return new R();
    }

    @PutMapping
    public R edit(@RequestBody SysArticle article){
        articleService.edit(article);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        articleService.deleteById(id);
        return new R ();
    }
}
