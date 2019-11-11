package cn.gdut.myblog.system.service;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.entity.SysComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<SysComment> {

    /**
     * 根据id查找评论
     * @param id 评论id
     * @return
     */
    SysComment findById(Long id);

    /**
     * 根据文章id或者评论信息
     * @param articleId
     * @return
     */
    List<SysComment> findAllByArticleId(Long articleId);

    /**
     * 根据文章id查找所有的评论信息
     * @param articleId
     * @return
     */
    SysComment findByArticleId(Long articleId);

    /**
     * 根据文章查找评论信息
     * @param article
     * @return
     */
    SysComment findByArticle(SysArticle article);

    /**
     * 根据文章获取该文章所有的评论信息
     * @param article
     * @return
     */
    List<SysComment> findAllByArticle(SysArticle article);

    /**
     * 添加评论
     * @param comment
     */
    void add(SysComment comment);

    /**
     * 分页查询
     * @param comment
     * @param queryPage
     * @return
     */
    IPage<SysComment> findByPage(SysComment comment, QueryPage queryPage);

    /**
     * 根据文章Id删除评论
     * @param ArticleId
     */
    void deleteByArticleId(Long ArticleId);

}
