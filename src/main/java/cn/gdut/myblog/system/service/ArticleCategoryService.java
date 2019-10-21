package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleCategoryService extends IService<ArticleCategory> {

    /**
     * 新增
     * @param articleCategory
     */
    void add(ArticleCategory articleCategory);
}
