package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.ArticleTag;
import cn.gdut.myblog.system.entity.SysTag;
import cn.gdut.myblog.system.mapper.TagMapper;
import cn.gdut.myblog.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, SysTag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Override
    public List<SysTag> findAll(){
        return tagMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public IPage<SysTag> findByPage(SysTag tag, QueryPage queryPage) {
        IPage<SysTag> page1 = new Page<>(queryPage.getPage(),queryPage.getLimit());
        // 构造查询的条件
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(tag.getName()),SysTag::getName,tag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectPage(page1,queryWrapper);
    }

    @Override
    @Transactional
    public void updata(SysTag tag) {
        tagMapper.updateById(tag);
    }

    @Override
    @Transactional
    public void delete(Long tagId) {
        // 删除tag表
        tagMapper.deleteById(tagId);
        // 先查找与article-tag表
        List<ArticleTag> articleTags = articleTagService.findByTagId(tagId);
        // 根据article-tag删除article表
        for (ArticleTag articleTag : articleTags){
            // 删除文章
            articleService.deleteById(articleTag.getArticleId());
            // 删除该该文章所有的评论
            commentService.deleteByArticleId(articleTag.getArticleId());
            // 根据articleId删除article-category表中的信息
            articleCategoryService.deleteByArticleId(articleTag.getArticleId());
            // 删除tag-article表
            articleTagService.deleteByArticleTagId(articleTag.getId());
        }
        // 删除article-tag表中的信息



    }

    @Override
    @Transactional
    public void add(SysTag tag) {
        tagMapper.insert(tag);
    }

    @Override
    @Transactional
    public SysTag findById(Long tagId) {
        return tagMapper.selectById(tagId);
    }

    @Override
    public List<SysTag> selectBatchIds(Collection<? extends Serializable> ids) {
        return tagMapper.selectBatchIds(ids);
    }

    @Override
    public List<SysTag> findByArticleId(Long articleId) {

        return tagMapper.findByArticleId(articleId);
    }


//    @Override
//    public List<SysTag> fingByIds(String ids) {
//
//        return tagMapper.selectBatchIds(@Param() Collection<? extends Serializable > ids);
//    }
}
