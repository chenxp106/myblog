package cn.gdut.myblog.tag;


import cn.gdut.myblog.system.entity.SysTag;
import cn.gdut.myblog.system.mapper.TagMapper;
import cn.gdut.myblog.system.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTags {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void tagsCount(){
        System.out.println(tagService.count());
    }

    @Test
    public void testMapper(){
        System.out.println(tagMapper.selectById(1L));
    }

    @Test
    public void findAll(){
        List<SysTag> tags = tagService.findAll();
        for (SysTag sysTag : tags){
            System.out.println(sysTag);
        }
    }
}
