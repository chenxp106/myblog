package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 新增
     * @param articleTag
     */
    void add(ArticleTag articleTag);

    /**
     * 根据articleId查找tags
     * @param articleId
     */
    List<ArticleTag> findByArticleId(Long articleId);
}
