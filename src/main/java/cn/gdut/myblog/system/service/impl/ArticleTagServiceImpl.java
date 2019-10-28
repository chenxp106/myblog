package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.ArticleTag;
import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.mapper.ArticleTagMapper;
import cn.gdut.myblog.system.service.ArticleTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper,ArticleTag > implements ArticleTagService {

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    @Transactional
    public void add(ArticleTag articleTag) {
        articleTagMapper.insert(articleTag);
    }

    @Override
    public List<ArticleTag> findByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleId);
        return articleTagMapper.selectList(queryWrapper);
    }

    @Override
    public List<ArticleTag> findByArticle(SysArticle article) {
        List<ArticleTag> articleTags = findByArticleId(article.getId());
        return articleTags;
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleId);
        articleTagMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public List<ArticleTag> findByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId, tagId);
        List<ArticleTag> articleTags = articleTagMapper.selectList(queryWrapper);
        return articleTags;
    }

    @Override
    @Transactional
    public void deleteByArticleTagId(Long id) {
        articleTagMapper.deleteById(id);
    }


}
