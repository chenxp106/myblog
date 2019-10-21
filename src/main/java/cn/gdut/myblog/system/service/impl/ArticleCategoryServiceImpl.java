package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.ArticleCategory;
import cn.gdut.myblog.system.mapper.ArticleCategoryMapper;
import cn.gdut.myblog.system.service.ArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public void add(ArticleCategory articleCategory) {
        articleCategoryMapper.insert(articleCategory);
    }
}
