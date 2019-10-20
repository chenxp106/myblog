package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysCategory;
import cn.gdut.myblog.system.mapper.CategoryMapper;
import cn.gdut.myblog.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, SysCategory> implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<SysCategory> findAll() {
        List<SysCategory> categories = categoryMapper.selectList(new LambdaQueryWrapper<>());
        return categories;
    }

    @Override
    public IPage<SysCategory> findByPage(SysCategory category, int page, int limit) {
        IPage<SysCategory> page1 = new Page<>(page,limit);
        // 构建查询表达式
        LambdaQueryWrapper<SysCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNoneBlank(category.getName()),SysCategory::getName,category.getName());
        wrapper.orderByDesc(SysCategory::getId);
        IPage<SysCategory> sysCategoryIPage = categoryMapper.selectPage(page1, wrapper);
        return sysCategoryIPage;
    }

    @Override
    public void add(SysCategory category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public void update(SysCategory category) {
        categoryMapper.updateById(category);
    }
}
