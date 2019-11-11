package cn.gdut.myblog.system.controller.router;

import cn.gdut.myblog.common.constants.CommonConstant;
import cn.gdut.myblog.system.entity.*;
import cn.gdut.myblog.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客前台路由控制层
 */
@Controller
public class SiteRouterController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping({"", "/"})
    public String index(Model model){
        IPage<SysArticle> page = new Page<>(1,12);
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        // 降序排序文章
        queryWrapper.orderByDesc(SysArticle::getId);
        // 只选择已经发表的文章
        queryWrapper.eq(SysArticle::getState, CommonConstant.DEFAULT_RELEASE_STATUS);
        IPage<SysArticle> list = articleService.page(page, queryWrapper);
        // 对所有的文章添加分类信息
        list.getRecords().forEach(article -> {
            //Jsoup是一个html解析器。
            String context = Jsoup.parse(article.getContent()).text();
            // 如果长度超过50，截取部分放入context
            if (context.length() > 50 ){
                context = context.substring(0,50) + "...";
            }
            article.setContent(context);
            article.setContentMd(null);

            // 设置分类信息
            if (StringUtils.isNoneBlank(article.getCategory())){
                // 设置分类信息
                SysCategory category = categoryService.getById(article.getCategory());
                if (category != null){
                    article.setCategory(category.getName());
                }
                else {
                    article.setCategory(null);
                }
            }
        });


        Map<String,Object> data = new HashMap<>();
        data.put("current", list.getCurrent());
        data.put("pages", list.getPages());
        data.put("rows",list.getRecords());
        data.put("total",list.getTotal());
        model.addAttribute("list",data);

        // 初始化


        return "site/index";
    }

    /**
     * 初始化博客的前台数据
     * @param model
     */
    private void init(Model model){

    }

    @RequestMapping("/article/{id}")
    public String article(@PathVariable String id, Model model){
        SysArticle article = articleService.findById(Long.valueOf(id));

        // 展示评论信息.根据文章获取当前文章的评论信息
//        SysComment comment = commentService.findByArticle(article);
//        Map<String, Object> data = new HashMap<>();
        // 查找当前文章的所有评论
        List<SysComment> comments = commentService.findAllByArticle(article);
        Map<String, Object> data = new HashMap<>();

        data.put("count",comments.size());
        data.put("rows", comments);
        data.put("total",1);
        data.put("current",1);
        model.addAttribute("comments",data);
        // 获取当前文章的tag
        List<ArticleTag> articleTags = articleTagService.findByArticleId(article.getId());
        List<SysTag> tags = new ArrayList<>();
        for (ArticleTag articleTag : articleTags){
            // 查找tag赋值给artcle.tag
            SysTag tag = tagService.findById(articleTag.getTagId());
            tags.add(tag);
        }
        article.setTags(tags);

        model.addAttribute("p",article);

        return "site/page/article_change";
    }
}
