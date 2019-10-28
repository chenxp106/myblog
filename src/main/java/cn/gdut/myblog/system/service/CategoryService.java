package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.SysArticle;
import cn.gdut.myblog.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<SysCategory> {

    /**
     * 查找全部
     * @return
     */
    List<SysCategory> findAll();

    IPage<SysCategory> findByPage(SysCategory category, int page, int limit);

    void add(SysCategory category);

    /*
    根据id删除分类
     */
    void delete(Long categoryId);

    void update(SysCategory category);

    /**
     * 根据文章查找所属的分类
     * @param article
     * @return
     */
    SysCategory findByArticle(SysArticle article);
}
