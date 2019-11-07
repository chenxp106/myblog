package cn.gdut.myblog.common.aspect;

import cn.gdut.myblog.common.exception.GlobalException;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.entity.SysUser;
import cn.gdut.myblog.system.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 切面类。切入点和通知的结合
/*@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    // 切入点。指我们要对哪些Jontponit连接点进行拦截。有切入点表达式.表示有Log需要拦截
    @Pointcut("@annotation(cn.gdut.myblog.common.annotation.Log)")
    public void pointcut(){

    }

    // 通知的类型，这里是环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException{

        Object result  = null;
        try {
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }

        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null){
            long beginTime = System.currentTimeMillis();
            // 获取Request
//            ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().get
            SysLog log = new SysLog();
            log.setIp("12.0.0.0");
            log.setUsername(sysUser.getUsername());
            logService.saveLog(proceedingJoinPoint, log);
        }
        return result;
    }

}*/

/**
 * 切面有切入点和通知组成。
 */
@Aspect
@Component
public class LogAspect{

    @Autowired
    LogService logService;

    // 申明切入点,这里用注解完成，表示注解有Log的就是一个接入点
    @Pointcut("@annotation(cn.gdut.myblog.common.annotation.Log)")
    public void pointcut(){

    }

    // 配置通知的类型，
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException{
        Object res = null;
        // 这里可以获取执行方法的参数
        try {
            res = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }

        // 日志记录交给LogServiceImpl完成
        logService.saveLog(proceedingJoinPoint);

        return res;
    }

}
