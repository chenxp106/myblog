package cn.gdut.myblog;

import cn.gdut.myblog.system.entity.SysComment;
import cn.gdut.myblog.system.entity.SysUser;
import cn.gdut.myblog.system.mapper.CommentMapper;
import cn.gdut.myblog.system.mapper.UserMapper;
import cn.gdut.myblog.system.service.ArticleService;
import cn.gdut.myblog.system.service.CommentService;
import cn.gdut.myblog.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    UserService userService;

    @Test
    public void mybatis01() {
        List<SysUser> users = userMapper.selectList(null);
        for (SysUser user : users){
            System.out.println("---------------");
            System.out.println(user);
        }
    }

    @Test
    public void userSysFindByName() {
        SysUser user = userService.findByName("tycoding");
        System.out.println("--------------");
        System.out.println(user);
    }

    @Test
    public void articleCount(){
        System.out.println(articleService.count());
    }

    @Test
    public void commentCount(){
        System.out.println("评论的数量为-------------");
        System.out.println(commentService.count());
    }


//    @Test
//    public void insertComment(){
//
//        SysComment sysComment = new SysComment();
//        sysComment.setId(1L);
//        sysComment.setPId(1L);
//        sysComment.setCId(1L);
//        sysComment.setArticleId(1L);
//        sysComment.setArticleTitle("三国演义");
//        sysComment.setName("陈小平");
//        sysComment.setCName("cxp");
//        sysComment.setTime(new Date());
//        sysComment.setContent("很好");
//        sysComment.setEmail("chenxp106@163.com");
//        sysComment.setUrl("www.baidu.com");
//        sysComment.setSort(1L);
//        sysComment.setIp("10.23.11.105");
//        sysComment.setDevice("windows");
//        sysComment.setAddress("江西赣州");
//        commentMapper.insert(sysComment);
//    }



}
