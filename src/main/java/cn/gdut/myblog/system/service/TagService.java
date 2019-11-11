package cn.gdut.myblog.system.service;


import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface TagService extends IService<SysTag> {

    /**
     * 查询全部
     * @return
     */
    public List<SysTag> findAll();

    /**
     * 分页查询
     * @param tag
     * @param page
     * @param limit
     * @return
     */
    IPage<SysTag> findByPage(SysTag tag, QueryPage queryPage);

    /**
     * 更新
     */
    void updata(SysTag tag);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    void add(SysTag tag);

    /**
     * 根据id查找tag
     * @param tagId
     * @return
     */
    SysTag findById(Long tagId);

    List<SysTag> selectBatchIds(@Param("ids")Collection<? extends Serializable> ids);

    /**
     * 根据文章id查找
     * @param articleId
     * @return
     */
    List<SysTag> findByArticleId(Long articleId);
}
