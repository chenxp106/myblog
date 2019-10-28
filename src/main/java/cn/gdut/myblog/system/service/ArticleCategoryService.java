package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleCategoryService extends IService<ArticleCategory> {

    /**
     * 新增
     * @param articleCategory
     */
    void add(ArticleCategory articleCategory);

    /**
     * 根据文章id删除文章-分类表
     * @param articleId
     */
    void deleteByArticleId(Long articleId);

    /**
     * 根据分类id删除
     * @param categoryId
     */
    void deleteByCategoryId(Long categoryId);
}
