package cn.gdut.myblog.commoms;

import cn.gdut.myblog.system.entity.SysComment;
import cn.gdut.myblog.system.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCommon {

    @Autowired
    CommentService commentService;

    @Test
    public void testFindById(){
        SysComment comment = commentService.findById(110L);
        System.out.println(comment);
    }

    @Test
    public void TestAllByArticleId(){
        List<SysComment> comments = commentService.findAllByArticleId(19L);
        for (SysComment comment : comments){
            System.out.println(comment);
        }
    }
}
