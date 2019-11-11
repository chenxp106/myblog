package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.controller.BaseController;
import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.service.LogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController {

    @Autowired
    LogService logService;

    @GetMapping("list")
    public R list(SysLog log, QueryPage queryPage){
        IPage<SysLog> selectPage = logService.findByPage(log,queryPage);
        return new  R <>(super.getData(selectPage));
    }

    @DeleteMapping("/{id}")
    @Log("删除日志")
    public R delete(@PathVariable ("id") Long id ){
        logService.delete(id);
        return new R<>();
    }

}
