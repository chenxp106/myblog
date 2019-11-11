package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.controller.BaseController;
import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysLoginLog;
import cn.gdut.myblog.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loginlog")
public class LoginLogController extends BaseController {

    @Autowired
    LoginLogService loginLogService;

    @GetMapping("/list")
    public R findByPage(SysLoginLog loginLog, QueryPage queryPage){
        return new R<>(super.getData(loginLogService.findByPage(loginLog, queryPage)));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        loginLogService.deleteById(id);
        return new R<>();
    }

}
