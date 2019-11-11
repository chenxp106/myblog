package cn.gdut.myblog.system.service;

import cn.gdut.myblog.common.utils.QueryPage;
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
     * @param article 文章
     * @param queryPage 分页查询的信息
     * @return
     */
    IPage<SysArticle> findByPage(SysArticle article, QueryPage queryPage);

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

    /**
     * 根据分类id删除
     * @param id
     */
    void deleteByCategoryId(Long id);

    /**
     * 根据分类id查找所有文章
     * @param categoryId
     * @return
     */
    List<SysArticle> findByCategoryId(Long categoryId);
}
