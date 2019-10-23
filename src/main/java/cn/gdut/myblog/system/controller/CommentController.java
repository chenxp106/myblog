package cn.gdut.myblog.system.controller;

import cn.gdut.myblog.common.utils.AddressUtil;
import cn.gdut.myblog.common.utils.IpUtil;
import cn.gdut.myblog.common.utils.R;
import cn.gdut.myblog.common.utils.UserAgent;
import cn.gdut.myblog.system.entity.SysComment;
import cn.gdut.myblog.system.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public R save(@RequestBody SysComment comment, HttpServletRequest request){
        /**
         * 将传入进来的common添加ip设备等信息
         */
        String ip = IpUtil.getIpAddr(request);
        comment.setIp(ip);
        comment.setTime(new Date());
        // ip地址的位置
        comment.setAddress(AddressUtil.getAddress(ip));
        // 浏览器divece
        comment.setDevice(UserAgent.getOsAndBrowserInfo(request));
        commentService.add(comment);
        return new  R<>();
    }


    @GetMapping("/list")
    public R list(SysComment comment,int page, int limit){

        IPage<SysComment> iPage = commentService.findByPage(comment,page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", iPage.getRecords());
        data.put("total",iPage.getTotal());
        return new R<>(data);
    }

}
