package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysTag;
import cn.gdut.myblog.system.mapper.TagMapper;
import cn.gdut.myblog.system.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, SysTag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<SysTag> findAll(){
        return tagMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public IPage<SysTag> list(SysTag tag, int page, int limit) {
        IPage<SysTag> page1 = new Page<>(page,limit);
        // 构造查询的条件
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(tag.getName()),SysTag::getName,tag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectPage(page1,queryWrapper);
    }

    @Override
    public void updata(SysTag tag) {
        tagMapper.updateById(tag);
    }

    @Override
    public void delete(Long id) {
        tagMapper.deleteById(id);

    }

    @Override
    public void add(SysTag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public SysTag findById(Long tagId) {
        return tagMapper.selectById(tagId);
    }

    @Override
    public List<SysTag> selectBatchIds(Collection<? extends Serializable> ids) {
        return tagMapper.selectBatchIds(ids);
    }



//    @Override
//    public List<SysTag> fingByIds(String ids) {
//
//        return tagMapper.selectBatchIds(@Param() Collection<? extends Serializable > ids);
//    }
}
