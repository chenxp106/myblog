package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.controller.BaseController;
import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysCategory;
import cn.gdut.myblog.system.service.CategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    public R findAll(){
        List<SysCategory> categories = categoryService.findAll();
        return new R<>(categories);
    }

    @GetMapping("/list")
    public R list(SysCategory category, QueryPage queryPage){
        IPage<SysCategory> page1 = categoryService.findByPage(category, queryPage);
        return new R<>(super.getData(page1));
    }

    @PostMapping
    public R add(@RequestBody SysCategory category){
        categoryService.add(category);
        return new R<>();
    }

    @GetMapping("/{id}")
    @Log("新增分类")
    public R add(@PathVariable Long id){
        categoryService.delete(id);
        return new R<>();
    }

    @PutMapping
    @Log("更新分类")
    public R edit(@RequestBody SysCategory category){
        categoryService.update(category);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除分类")
    public R delete(@PathVariable Long id){
        categoryService.delete(id);
        return new R<>();
    }

}
