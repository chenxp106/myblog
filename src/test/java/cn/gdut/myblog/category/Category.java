package cn.gdut.myblog.category;

import cn.gdut.myblog.system.entity.SysCategory;
import cn.gdut.myblog.system.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Category {

    @Autowired
    CategoryService categoryService;

    @Test
    public void findAll(){
        List<SysCategory> categories = categoryService.findAll();
        for (SysCategory sysCategory : categories){
            System.out.println(sysCategory);
        }
    }
}
