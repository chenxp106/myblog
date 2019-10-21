package cn.gdut.myblog.link;

import cn.gdut.myblog.system.entity.SysLink;
import cn.gdut.myblog.system.service.LinkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLink {

    @Autowired
    LinkService linkService;

    @Test
    public void test(){

        List<SysLink> links = linkService.findAll();
        for (SysLink link : links){
            System.out.println(link);
        }
    }

}
