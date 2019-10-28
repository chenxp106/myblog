package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.ArticleTag;
import cn.gdut.myblog.system.entity.SysArticle;
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

    /**
     * 根据文章查找该文章的tags
     * @param article
     * @return
     */
    List<ArticleTag> findByArticle(SysArticle article);

    /**
     * 根据文章id删除文章-标签表
     * @param articleId
     */
    void deleteByArticleId(Long articleId);

    /**
     * 根据tagId查找所有
     * @param tagId
     * @return
     */
    List<ArticleTag> findByTagId(Long tagId);

    /**
     * 根据主键id删除
     * @param id
     */
    void deleteByArticleTagId(Long id);
}
