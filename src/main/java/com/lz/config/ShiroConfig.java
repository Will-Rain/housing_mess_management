package com.lz.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/***
 * @author will
 * @date 2020/03/06 9:31
 */

@Configuration
public class ShiroConfig {

    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
//    @Bean
//    public EhCacheManager ehCacheManager(){
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return cacheManager;
//    }


    //ShiroFilterFactoryBean：第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Autowired DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

//        anon:    无需认证即可访问
//        authc:   需要认证才可访问
//        user:    点击“记住我”功能可访问
//        perms:   拥有权限才可以访问
//        role:    拥有某个角色权限才能访问

        //添加shiro的内置过滤器
        Map<String,String> filterMap = new LinkedHashMap<>();


        //拦截
        //filterMap.put("/user/*","authc");//对一个包下的html页面拦截
        //filterMap.put("/update","authc");
        //授权

        //放行
        filterMap.put("/pages/login.html","anon");
        filterMap.put("/login","anon");

        filterMap.put("/css/**","anon");
        filterMap.put("/data/**","anon");
        filterMap.put("/images/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/lib/**","anon");
        filterMap.put("/error/**","anon");
        filterMap.put("/pages/unauthorized.html","anon");

        //拦截，需要登录才能访问
//        filterMap.put("/","authc");
//        filterMap.put("/index","authc");
//        filterMap.put("/index.html","authc");
//        filterMap.put("/administrator/*","authc");


        filterMap.put("/**","authc");


//        filterMap.put("/administrator/addAdmin","perms[1]");
//        filterMap.put("/administrator/deleteAdmin","perms[1]");
//        filterMap.put("pages/building/building-list.html","perms[1]");


        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("pages/login.html");//设置登录的请求
        bean.setUnauthorizedUrl("pages/unauthorized.html");//未授权页面


        return bean;
    }

    //DefaultWebSecurityManager：第二步
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Autowired UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 UserRealm
        securityManager.setRealm(userRealm);
        //配置 ehcache缓存管理器 参考博客：
//        securityManager.setCacheManager(ehCacheManager());

        return securityManager;
    }
    //创建 realm 对象，需要自定义该类：第一步
    @Bean
    public UserRealm getUserRealm(){
        UserRealm UserRealm = new UserRealm();

//        UserRealm.setCachingEnabled(true);
//        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
//        UserRealm.setAuthenticationCachingEnabled(true);
//        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
//        UserRealm.setAuthenticationCacheName("authenticationCache");
//        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
//        UserRealm.setAuthorizationCachingEnabled(true);
//        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
//        UserRealm.setAuthorizationCacheName("authorizationCache");

        return UserRealm;
    }

    //shiro整合thymeleaf。在头部加上命名空间xmlns:shiro="http://www.pollix.at/thymeleaf/shiro"
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}