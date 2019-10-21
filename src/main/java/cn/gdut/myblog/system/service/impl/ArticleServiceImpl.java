package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.*;
import cn.gdut.myblog.system.mapper.ArticleMapper;
import cn.gdut.myblog.system.service.ArticleCategoryService;
import cn.gdut.myblog.system.service.ArticleService;
import cn.gdut.myblog.system.service.ArticleTagService;
import cn.gdut.myblog.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<SysArticle> findAll() {
        List<SysArticle> articles = articleMapper.selectList(new LambdaQueryWrapper<>());
        return articles;
    }

    @Override
    public IPage<SysArticle> findByPage(SysArticle article, int page, int limit) {
        IPage<SysArticle> iPage = new Page<>(page, limit);
        LambdaQueryWrapper<SysArticle> wrapper = new LambdaQueryWrapper<>();
        // 按标题和作者查询
        wrapper.like(StringUtils.isNoneBlank(article.getTitle()), SysArticle::getTitle, article.getTitle());
        wrapper.like(StringUtils.isNoneBlank(article.getAuthor()), SysArticle::getAuthor, article.getAuthor());
        // 降序排序
        wrapper.orderByDesc(SysArticle::getId);
        IPage<SysArticle> page1 = articleMapper.selectPage(iPage, wrapper);
        return page1;
    }

    @Override
    public void add(SysArticle article) {
        // 将作者,创建时间，修改时间完善。
        article.setAuthor(((SysUser)SecurityUtils.getSubject().getPrincipal()).getUsername());
        article.setCreateTime(new Date());
        article.setEditTime(new Date());
        articleMapper.insert(article);
        // 将tag保存起来
        if (article.getTags() != null && article.getTags().size() > 0){
            for (SysTag tag : article.getTags()){
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tag.getId());
                articleTag.setArticleId(article.getId());
                articleTagService.add(articleTag);
            }
        }
        // 分类信息保存
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(article.getId());
        SysCategory sysCategory = categoryService.getById(article.getCategory());
        articleCategory.setCatrgoryId(sysCategory.getId());
        articleCategoryService.add(articleCategory);

    }

    @Override
    public SysArticle findById(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public void edit(SysArticle article) {
        article.setEditTime(new Date());
        articleMapper.updateById(article);
    }

    @Override
    public void deleteById(Long id) {
        articleMapper.deleteById(id);
    }


}
