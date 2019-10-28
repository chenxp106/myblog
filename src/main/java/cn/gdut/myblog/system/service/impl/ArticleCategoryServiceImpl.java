package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.ArticleCategory;
import cn.gdut.myblog.system.mapper.ArticleCategoryMapper;
import cn.gdut.myblog.system.service.ArticleCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    @Transactional
    public void add(ArticleCategory articleCategory) {
        articleCategoryMapper.insert(articleCategory);
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ArticleCategory::getArticleId, articleId);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long categoryId) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getCatrgoryId,categoryId);
        articleCategoryMapper.delete(queryWrapper);
    }
}
