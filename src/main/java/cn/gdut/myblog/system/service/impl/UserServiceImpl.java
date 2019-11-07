package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.system.entity.SysUser;
import cn.gdut.myblog.system.mapper.UserMapper;
import cn.gdut.myblog.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,username);
        List<SysUser> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void update(SysUser sysUser) {
        sysUser.setId(findByName(sysUser.getUsername()).getId());
        userMapper.updateById(sysUser);
    }
}
