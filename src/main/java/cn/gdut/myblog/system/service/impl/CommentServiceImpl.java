package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysComment;
import cn.gdut.myblog.system.mapper.CommentMapper;
import cn.gdut.myblog.system.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, SysComment> implements CommentService {

}
