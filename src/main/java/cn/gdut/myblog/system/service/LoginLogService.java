package cn.gdut.myblog.system.service;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginLogService extends IService<SysLoginLog> {

    void saveLog(SysLoginLog loginLog);

    IPage<SysLoginLog> findByPage(SysLoginLog sysLoginLog, QueryPage queryPage);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Long id);
}
