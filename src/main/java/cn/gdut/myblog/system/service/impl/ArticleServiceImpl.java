package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.*;
import cn.gdut.myblog.system.mapper.ArticleMapper;
import cn.gdut.myblog.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    TagService tagService;

    @Override
    public List<SysArticle> findAll() {
        List<SysArticle> articles = articleMapper.selectList(new LambdaQueryWrapper<>());
        return articles;
    }

    @Override
    public IPage<SysArticle> findByPage(SysArticle article, QueryPage queryPage) {
        IPage<SysArticle> iPage = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysArticle> wrapper = new LambdaQueryWrapper<>();
        // 按标题和作者查询
        wrapper.like(StringUtils.isNoneBlank(article.getTitle()), SysArticle::getTitle, article.getTitle());
        wrapper.like(StringUtils.isNoneBlank(article.getAuthor()), SysArticle::getAuthor, article.getAuthor());
        // 降序排序
        wrapper.orderByDesc(SysArticle::getId);
        IPage<SysArticle> selectPage = articleMapper.selectPage(iPage, wrapper);
        initPage(selectPage.getRecords());
        return selectPage;
    }

    private void initPage(List<SysArticle> articles){
        for (SysArticle article : articles){
            //查找文章的分类信息
            SysCategory category = categoryService.findByArticle(article);
            //设置category为名称而不是数字
            article.setCategory(category.getName());
            // 查找该文章所有的tags
            List<ArticleTag> articleTags = articleTagService.findByArticle(article);
            //
            List<SysTag> tags = new ArrayList<>();
            for (ArticleTag articleTag: articleTags){
                SysTag tag = tagService.findById(articleTag.getTagId());
                tags.add(tag);
            }
            article.setTags(tags);
        }


    }

    @Override
    @Transactional
    public void add(SysArticle article) {
        // 将作者,创建时间，修改时间完善。
        article.setAuthor(((SysUser)SecurityUtils.getSubject().getPrincipal()).getUsername());
        article.setCreateTime(new Date());
        article.setEditTime(new Date());
        articleMapper.insert(article);
//        int i = 1/0;
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
    @Transactional
    public void edit(SysArticle article) {
        article.setEditTime(new Date());
        articleMapper.updateById(article);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 删除类别，判断id的合法性
        if (id != null && id != 0){
            articleMapper.deleteById(id);
            // 同时删除文章-分类表
            articleCategoryService.deleteByArticleId(id);
            // 删除文章-标签表
            articleTagService.deleteByArticleId(id);
        }

    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticle::getCategory, id);
        articleMapper.delete(queryWrapper);
    }

    /**
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<SysArticle> findByCategoryId(Long categoryId) {
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticle::getCategory, categoryId);
        List<SysArticle> articles = articleMapper.selectList(queryWrapper);
        return articles;
    }


}
