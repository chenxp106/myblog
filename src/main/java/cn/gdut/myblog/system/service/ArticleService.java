package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文章server
 */
public interface ArticleService  extends IService<SysArticle> {

    /**
     * 查找全部
     * @return
     */
    List<SysArticle> findAll();

    /**
     * 分页查询
     * @param article 文章实体，携带查询的条件
     * @param page 当前页
     * @param limit 每页展示的条目
     * @return 封装好的Article
     */
    IPage<SysArticle> findByPage(SysArticle article, int page, int limit);

    /**
     * 新增
     */
    void add(SysArticle article);

    /**
     * 根据id查找文章
     * @param id
     * @return
     */
    SysArticle findById(Long id);

    /**
     * 编辑文章
     * @param article
     */
    void edit(SysArticle article);

    void deleteById(Long id);
}
