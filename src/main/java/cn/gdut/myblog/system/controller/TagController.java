package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysTag;
import cn.gdut.myblog.system.service.TagService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping("/findAll")
    public R findAll(){

        return new R<>(tagService.findAll());
    }

    /**
     * 分页查询
     * @param tag 属性
     * @param page  当前页
     * @param limit limit
     * @return
     */
    @RequestMapping("/list")
    public R findByPage(SysTag tag, int page, int limit){
        IPage<SysTag> tags = tagService.list(tag,page, limit);
        Map<String, Object > data = new HashMap<>();
        data.put("rows", tags.getRecords());
        data.put("total",tags.getTotal());
        return new R<>(data);
    }

    @PutMapping
    public R edit(@RequestBody SysTag tag){
        tagService.updata(tag);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        tagService.delete(id);
        return new R<>();
    }

    @PostMapping
    public R add(@RequestBody SysTag tag){
        tagService.add(tag);
        return new R<>();
    }
}
