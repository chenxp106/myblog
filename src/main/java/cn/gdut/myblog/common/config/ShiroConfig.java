package cn.gdut.myblog.common.config;


import cn.gdut.myblog.common.properties.MyBlogProperties;
import cn.gdut.myblog.common.properties.ShiroProperties;
import cn.gdut.myblog.common.realm.AuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private MyBlogProperties properties;

    /**
     * 安全管理器，必须要配置
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm(hashedCredentialsMatcher()));
        return securityManager;
    }

    @Bean
    public Realm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * session 管理器,使用默认的session管理器，设置session过期时间。
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        ShiroProperties shiro = properties.getShiro();
        DefaultSessionManager session = new DefaultSessionManager();
        //session过期时间，有效时间1000*60*30=180 000，单位毫秒
        session.setGlobalSessionTimeout(shiro.getSessionTimeout());
        //相隔多久检查一次session的有效性,设置为默认的
        session.setDeleteInvalidSessions(true);
        session.setSessionValidationSchedulerEnabled(true);
        session.setSessionDAO(new EnterpriseCacheSessionDAO());

        return session;
    }

    @Bean
    public SimpleCookie remeberMeCookie(){
        ShiroProperties shiro = properties.getShiro();
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiro.getCookieTimeout());
        return simpleCookie;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroProperties shiro = properties.getShiro();
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        filter.setLoginUrl(shiro.getLoginUrl());
        filter.setSuccessUrl(shiro.getSuccessUrl());

        Map<String, String> filterChain = new LinkedHashMap<>();
        String[] urls = shiro.getAnonUrl().split(",");
        // 任何匿名都可以访问
        for (String url : urls){
            filterChain.put(url, "anon");
        }
        filterChain.put("/**","user");
        filter.setFilterChainDefinitionMap(filterChain);
//        filter.setFilters();

        return filter;
    }

}
