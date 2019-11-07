package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysLink;
import cn.gdut.myblog.system.service.LinkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/link")
public class LinkController {

    @Autowired
    LinkService linkService;


    @GetMapping("/list")
    public R findByPage(SysLink sysLink, int page, int limit){

        IPage<SysLink> page1 = linkService.list(sysLink, page, limit);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",page1.getRecords());
        map.put("total",page1.getTotal());
        return new R<>(map);
    }

    @PostMapping
    @Log("更新链接")
    public R edit(@RequestBody SysLink link){
        linkService.save(link);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除链接")
    public R delete(@PathVariable int id){
        linkService.removeById(id);
        return new R<>();
    }
}
