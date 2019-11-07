package cn.gdut.myblog.system.service;

import cn.gdut.myblog.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<SysUser> {

    SysUser findByName(String username);

    /**
     * 更新用户
     * @param sysUser
     */
    void update(SysUser sysUser);
}
