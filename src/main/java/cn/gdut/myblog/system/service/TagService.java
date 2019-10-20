package cn.gdut.myblog.system.service;


import cn.gdut.myblog.system.entity.SysTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

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
    IPage<SysTag> list(SysTag tag, int page, int limit);

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
}
