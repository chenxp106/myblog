package cn.gdut.myblog.article;

import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestArticle {

    @Autowired
    ArticleService articleService;

    @Test
    public void findAll(){
        List<SysArticle> articles = articleService.findAll();
        for (SysArticle article : articles){
            System.out.println(article);
        }
    }
}
