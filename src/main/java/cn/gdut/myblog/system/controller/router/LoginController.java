package cn.gdut.myblog.system.controller.router;

import cn.gdut.myblog.common.constants.CommonConstant;
import cn.gdut.myblog.common.utils.*;
import cn.gdut.myblog.system.entity.SysLoginLog;
import cn.gdut.myblog.system.service.LoginLogService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginLogService loginLogService;

    @PostMapping("/login")
    public R login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        //获取IP和device等信息
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ipAddr = IpUtil.getIpAddr(request);
        // 记录登录信息
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIp(ipAddr);
        sysLoginLog.setLocation(AddressUtil.getAddress(ipAddr));
        sysLoginLog.setCreateTime(new Date());
        // 获取device
        String header = request.getHeader(CommonConstant.USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        // 获取操作系统
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 获取浏览器信息
        Browser browser = userAgent.getBrowser();
        sysLoginLog.setDevice(browser + "--" +operatingSystem);
        loginLogService.saveLog(sysLoginLog);
//        return "admin/index";
        Map<String, Object> map = new HashMap<>();
        map.put("token",subject.getSession().getId());
        map.put("user",SecurityUtils.getSubject().getPrincipal());
        return new R<>(map);
    }

    @GetMapping("/logout")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new R();
    }
}
