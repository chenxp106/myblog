package cn.gdut.myblog.system.service.impl;

import cn.gdut.myblog.common.annotation.Log;
import cn.gdut.myblog.common.utils.AddressUtil;
import cn.gdut.myblog.common.utils.HttpContextUtil;
import cn.gdut.myblog.common.utils.IpUtil;
import cn.gdut.myblog.common.utils.QueryPage;
import cn.gdut.myblog.system.entity.SysLog;
import cn.gdut.myblog.system.entity.SysUser;
import cn.gdut.myblog.system.mapper.LogMapper;
import cn.gdut.myblog.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public IPage<SysLog> findByPage(SysLog sysLog, QueryPage queryPage) {
        IPage<SysLog> page1 = new Page<>(queryPage.getPage(), queryPage.getLimit());
        // 查询条件
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        // 根据成名查询
        queryWrapper.like(StringUtils.isNoneBlank(sysLog.getOperation()), SysLog::getOperation, sysLog.getOperation());
        // 根据ip查询
        queryWrapper.like(StringUtils.isNoneBlank(sysLog.getIp()), SysLog::getIp, sysLog.getIp());
        IPage<SysLog> selectPage = logMapper.selectPage(page1, queryWrapper);
        return selectPage;
    }

    /**
     * 保存日志文件
     * @param proceedingJoinPoint
     * @throws JsonProcessingException
     */
    @Override
    public void saveLog(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {

        SysLog log = new SysLog();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //对user的一些内容赋值
        if (sysUser != null){
            long beginTime = System.currentTimeMillis();
            // 获取请求的context用于获取IP
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            // 设置Ip
            String ip = IpUtil.getIpAddr(request);
            log.setIp(ip);
            long time = System.currentTimeMillis() - beginTime;
            log.setTime(time);
            log.setUsername(sysUser.getUsername());
        }


        // 获取注解上的操作名称.通过反射的方式获取
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        // 通过反射获取类
        Log annotation = method.getAnnotation(Log.class);
        if (annotation != null){
            log.setOperation(annotation.value());
        }
        // 获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = signature.getName();
        // 设置调用的方法名
        log.setMethod(className+ "."+methodName+ "()");
        // 处理方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer d = new LocalVariableTableParameterNameDiscoverer();
        // 请求参数的名称
        String[] parameterNames = d.getParameterNames(method);
        if (args != null && parameterNames != null){
            // 处理请求参数
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(parameterNames));
            String str = params.toString();
            if (str.length() > 100){
                str = str.substring(0,80) +"...";
                log.setParams(str);
            }
        }
        // 设置创建时间
        log.setCreateTime(new Date());
        log.setLocation(AddressUtil.getAddress(log.getIp()));
        this.save(log);
    }

    @Override
    public void delete(Long id) {
        logMapper.deleteById(id);
    }

    /**
     * 处理参数
     * @param params
     * @param args
     * @param paramNames
     * @return
     * @throws JsonProcessingException
     */
    private StringBuilder handleParams(StringBuilder params, Object [] args, List<String> paramNames) throws JsonProcessingException{
        for (int i = 0;i < args.length;i++){
            // 如果是Map
            if (args[i] instanceof Map){
                Set set = ((Map)args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList();
                for (Object key : set){
                    list.add(((Map)args[i]).get(key));
                    paramList.add(key);
                }
                // 递归处理
                return handleParams(params, list.toArray(), paramList);
            }
            else {
                if (args[i] instanceof Serializable){
                    Class<?> clazz = args[i].getClass();
                    try {
                        clazz.getDeclaredMethod("toString",new Class[]{null});
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i]));
                    }
                    catch (NoSuchMethodException e){
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                }
            }
        }
        return params;
    }


}
