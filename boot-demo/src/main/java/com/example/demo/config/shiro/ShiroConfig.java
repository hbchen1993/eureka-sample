package com.example.demo.config.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class ShiroConfig {
    // 将自己的验证方式加入容器

    @Autowired
    private RedisManager redisManager;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        // Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        redisTemplate.setKeySerializer(stringSerializer);
        // redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }

    @Bean
    MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    // 权限管理，配置主要是Realm的管理认证
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        manager.setSessionManager(SessionManager());
        return manager;
    }
    //
    // public RedisManager redisManager() {
    // RedisManager redisManager = new RedisManager();
    // redisManager.setHost("127.0.0.1:6379");
    // redisManager.setPassword("root123456");
    // return redisManager;
    // }

    /**
     * cacheManager
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }

    /**
     * redisSessionDAO
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * sessionManager
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    // /**
    // * 配置会话管理器，设定会话超时及保存
    // *
    // * @return
    // */
    // @Bean
    // public SessionManager sessionManager() {
    // DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    // Collection<SessionListener> listeners = new ArrayList<SessionListener>();
    // // 配置监听
    // listeners.add(sessionListener());
    // sessionManager.setSessionListeners(listeners);
    // sessionManager.setSessionIdCookie(sessionIdCookie());
    // sessionManager.setSessionDAO(redisSessionDAO());
    //
    // // 全局会话超时时间（单位毫秒），默认30分钟 暂时设置为10秒钟 用来测试
    // sessionManager.setGlobalSessionTimeout(1800000);// 单位毫秒
    // // 是否开启删除无效的session对象 默认为true
    // sessionManager.setDeleteInvalidSessions(true);
    // // 是否开启定时调度器进行检测过期session 默认为true
    // sessionManager.setSessionValidationSchedulerEnabled(true);
    // // 设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
    // // 设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler
    // // 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
    // sessionManager.setSessionValidationInterval(3600000);// 单位毫秒
    // // 取消url 后面的 JSESSIONID，设置为false为取消
    // sessionManager.setSessionIdUrlRewritingEnabled(false);
    // return sessionManager;
    //
    // }

    // 凭证匹配器（密码校验交给Shiro的SimpleAuthenticationInfo进行处理)
    // @Bean
    // public HashedCredentialsMatcher hashedCredentialsMatcher() {
    // HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    // hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
    // hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
    // return hashedCredentialsMatcher;
    // }

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        Map<String, String> filterMap = new HashMap<String, String>();
        // 登出
        // filterMap.put("/logout", "logout");
        // swagger
        // filterMap.put("/swagger**/**", "anon");
        // filterMap.put("/webjars/**", "anon");
        // filterMap.put("/v2/**", "anon");
        // filterMap.put("/doLogin", "anon");
        // filterMap.put("/doLogout", "anon");
        // filterMap.put("/test/testMethod", "anon");
        // 对所有用户认证
        // filterMap.put("/**", "authc");
        filterMap.put("/**", "anon");
        // 登录
        // bean.setLoginUrl("/login");
        // 首页
        // bean.setSuccessUrl("/index");
        // 未授权页面，认证不通过跳转
        // bean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    // 开启shiro aop注解支持.
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    // shiro注解模式下，登录失败或者是没有权限都是抛出异常，并且默认的没有对异常做处理，配置一个异常处理
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
        mappings.setProperty("UnauthorizedException", "/403");
        r.setExceptionMappings(mappings); // None by default
        r.setDefaultErrorView("error"); // No default
        r.setExceptionAttribute("exception"); // Default is "exception"
        return r;
    }
}
