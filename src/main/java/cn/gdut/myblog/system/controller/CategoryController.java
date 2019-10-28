package cn.gdut.myblog.system.controller;

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
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    public R findAll(){
        List<SysCategory> categories = categoryService.findAll();
        return new R<>(categories);
    }

    @GetMapping("/list")
    public R list(SysCategory category, int page, int limit){
        IPage<SysCategory> page1 = categoryService.findByPage(category, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page1.getRecords());
        data.put("total",page1.getTotal());
        return new R<>(data);
    }

    @PostMapping
    public R add(@RequestBody SysCategory category){
        categoryService.add(category);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R add(@PathVariable Long id){
        categoryService.delete(id);
        return new R<>();
    }

    @PutMapping
    public R edit(@RequestBody SysCategory category){
        categoryService.update(category);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        categoryService.delete(id);
        return new R<>();
    }

}
