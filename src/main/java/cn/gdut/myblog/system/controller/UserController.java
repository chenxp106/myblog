package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.utils.IpUtil;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.system.entity.SysUser;
import cn.gdut.myblog.system.service.ArticleService;
import cn.gdut.myblog.system.service.CommentService;
import cn.gdut.myblog.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    /**
     * 返回用户的信息，其中包括发表的文章个数、用户的信息。token值
     * @return
     */
    @GetMapping("/info")
    public R getInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("articleCount",articleService.count());
        map.put("commentCount",commentService.count());
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        map.put("user",user);
        String ip = IpUtil.getIpAddr(request);
        map.put("todayIp",ip);
        Serializable token = SecurityUtils.getSubject().getSession().getId();
        map.put("token",token);
        return new R<>(map);
    }

    @PutMapping
    @Log("更新用户")
    public R edit(@RequestBody SysUser sysUser){
        userService.update(sysUser);
        return new R<>();
    }
}
