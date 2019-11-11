package cn.gdut.myblog.system.service;

import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;

public interface LogService extends IService<SysLog> {

    /**
     * 分页查询
     * @param sysLog
     * @param page
     * @param limit
     * @return
     */
    IPage<SysLog> findByPage(SysLog sysLog, QueryPage queryPage);

    void saveLog(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException;

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
