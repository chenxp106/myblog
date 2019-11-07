package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.service.LogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("list")
    public R list(SysLog log, int page, int limit){
        IPage<SysLog> selectPage = logService.findByPage(log, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", selectPage.getRecords());
        data.put("total", selectPage.getTotal());

        return new  R <>(data);
    }

    @DeleteMapping("/{id}")
    @Log("删除日志")
    public R delete(@PathVariable ("id") Long id ){
        logService.delete(id);
        return new R<>();
    }

}
