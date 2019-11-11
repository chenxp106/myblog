package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.entity.SysLoginLog;
import cn.gdut.myblog.system.mapper.LoginLogMapper;
import cn.gdut.myblog.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, SysLoginLog> implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public void saveLog(SysLoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Override
    public IPage<SysLoginLog> findByPage(SysLoginLog sysLoginLog, QueryPage queryPage) {
        IPage<SysLoginLog> page1 = new Page<>(queryPage.getPage(),queryPage.getLimit());
        LambdaQueryWrapper<SysLoginLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysLoginLog::getId);
        IPage<SysLoginLog> sysLoginLogIPage = loginLogMapper.selectPage(page1, queryWrapper);
        return sysLoginLogIPage;
    }

    @Override
    public void deleteById(Long id) {
        loginLogMapper.deleteById(id);
    }
}
