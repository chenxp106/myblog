package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.entity.SysComment;
import cn.gdut.myblog.system.mapper.CommentMapper;
import cn.gdut.myblog.system.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, SysComment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public SysComment findById(Long id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<SysComment> findAllByArticleId(Long articleId) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComment::getArticleId, articleId);
        List<SysComment> comments = commentMapper.selectList(queryWrapper);
        return comments;
    }

    @Override
    public SysComment findByArticleId(Long articleId) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComment::getArticleId, articleId);
        SysComment comment = commentMapper.selectOne(queryWrapper);

        return comment;
    }

    @Override
    public SysComment findByArticle(SysArticle article) {
        return null;
    }


    @Override
    public List<SysComment> findAllByArticle(SysArticle article) {
        return findAllByArticleId(article.getId());
    }

//    @Override
//    public SysComment findByArticleId(Long articleId) {
//        //　暂时只查找一条记录
//        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysComment::getArticleId, articleId);
//        SysComment comment = commentMapper.selectOne(queryWrapper);
//        return comment;
//    }





}
