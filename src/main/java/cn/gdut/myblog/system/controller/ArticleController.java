package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
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

        Map<String, Object> data = new HashMap<>();
        IPage<SysArticle> page1 = articleService.findByPage(article, page, limit);
        data.put("rows", page1.getRecords());
        data.put("total", page1.getTotal());
        //
        return new R(data);
    }

    @PostMapping
    @Log("新增文章")
    public R save(@RequestBody SysArticle sysArticle){
        System.out.println(sysArticle);
        articleService.add(sysArticle);
        return new R();
    }

    // 连接点 这个值得是方法。被拦截的点。指的是要进行日志处理的方法
    @PutMapping
    @Log("更新文章")
    public R edit(@RequestBody SysArticle article){
        articleService.edit(article);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除文章")
    public R delete(@PathVariable Long id){
        articleService.deleteById(id);
        return new R ();
    }
}
