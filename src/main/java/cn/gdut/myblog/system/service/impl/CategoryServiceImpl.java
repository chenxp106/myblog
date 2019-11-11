package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.entity.SysCategory;
import cn.gdut.myblog.system.mapper.CategoryMapper;
import cn.gdut.myblog.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, SysCategory> implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    CommentService commentService;

    @Override
    public List<SysCategory> findAll() {
        List<SysCategory> categories = categoryMapper.selectList(new LambdaQueryWrapper<>());
        return categories;
    }

    @Override
    public IPage<SysCategory> findByPage(SysCategory category, QueryPage queryPage) {
        IPage<SysCategory> page1 = new Page<>(queryPage.getPage(),queryPage.getLimit());
        // 构建查询表达式
        LambdaQueryWrapper<SysCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNoneBlank(category.getName()),SysCategory::getName,category.getName());
        wrapper.orderByDesc(SysCategory::getId);
        IPage<SysCategory> sysCategoryIPage = categoryMapper.selectPage(page1, wrapper);
        return sysCategoryIPage;
    }

    @Override
    @Transactional
    public void add(SysCategory category) {
        categoryMapper.insert(category);
    }

    /**
     * 删除分类信息，需要删除与该分类相关的文章
     * @param categoryId categoryId
     */
    @Override
    @Transactional
    public void delete(Long categoryId) {
        //先根据categoryId 查找文章
        List<SysArticle> articles = articleService.findByCategoryId(categoryId);
        for (SysArticle article:articles){
            articleTagService.deleteByArticleId(article.getId());
            // 删除评论信息
            commentService.deleteByArticleId(article.getId());
        }
        // 删除分类信息表
        categoryMapper.deleteById(categoryId);
        //  根据分类信息删除article表
        articleService.deleteByCategoryId(categoryId);
        // 根据categoryId删除category-article表
        articleCategoryService.deleteByCategoryId(categoryId);
        // 删除tag相关信息


    }

    @Override
    @Transactional
    public void update(SysCategory category) {
        categoryMapper.updateById(category);
    }

    @Override
    public SysCategory findByArticle(SysArticle article) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCategory::getId,article.getCategory());
        SysCategory category = categoryMapper.selectOne(queryWrapper);
        return category;
    }

    @Override
    public SysCategory findByCategoryId(Long id) {
        return categoryMapper.selectById(id);
    }
}
