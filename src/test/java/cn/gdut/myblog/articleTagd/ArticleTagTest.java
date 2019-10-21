package cn.gdut.myblog.articleTagd;

import cn.gdut.myblog.system.entity.ArticleTag;
import cn.gdut.myblog.system.service.ArticleTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTagTest {

    @Autowired
    ArticleTagService articleTagService;
    @Test
    public void findByArticleId(){
        List<ArticleTag> articleTags = articleTagService.findByArticleId(19L);
        for (ArticleTag tag : articleTags){
            System.out.println(tag);
        }
    }
}
