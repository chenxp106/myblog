package cn.gdut.myblog.system.service;

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

    void delete(int id);

    void update(SysCategory category);
}
