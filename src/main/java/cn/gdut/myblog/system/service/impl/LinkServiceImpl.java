package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysLink;
import cn.gdut.myblog.system.mapper.LinkMapper;
import cn.gdut.myblog.system.service.LinkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl  extends ServiceImpl<LinkMapper, SysLink>  implements LinkService {

    @Autowired
    LinkMapper linkMapper;

    public List<SysLink> findAll(){
        QueryWrapper wrapper = new QueryWrapper();
        List<SysLink> links= linkMapper.selectList(wrapper);
        return links;
    }

    @Override
    public IPage<SysLink> findByPage(SysLink link, QueryPage queryPage) {

        IPage<SysLink> linkIPage = new Page<>(queryPage.getPage(),queryPage.getLimit());
        LambdaQueryWrapper<SysLink> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNoneBlank(link.getName()),SysLink::getName, link.getName());
        wrapper.orderByDesc(SysLink::getId);
        return linkMapper.selectPage(linkIPage,wrapper);
    }


}
