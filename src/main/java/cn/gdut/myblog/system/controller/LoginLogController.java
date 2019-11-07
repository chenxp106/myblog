package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysLoginLog;
import cn.gdut.myblog.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/loginlog")
public class LoginLogController {

    @Autowired
    LoginLogService loginLogService;

    @GetMapping("/list")
    public R findByPage(SysLoginLog loginLog,int page, int limit){
        IPage<SysLoginLog> page1 = loginLogService.findByPage(loginLog, page, limit);
        Map<String,Object> date = new HashMap<>();
        date.put("rows",page1.getRecords());
        date.put("total",page1.getTotal());
        return new R<>(date);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        loginLogService.deleteById(id);
        return new R<>();
    }

}
