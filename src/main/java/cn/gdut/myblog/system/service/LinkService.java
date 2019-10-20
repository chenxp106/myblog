package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.SysLink;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LinkService extends IService<SysLink> {

    public List<SysLink> findAll();

    IPage<SysLink> list(SysLink link,int page,int limit);


}
