import cn.muses.trade.core.configuration.NettyProperties;
import cn.muses.trade.core.context.HawkContext;
import cn.muses.trade.core.core.common.NettySpringContextUtils;
import cn.muses.trade.netty.codec.DefaultCodec;
import cn.muses.trade.netty.dispatcher.HawkRequestDispatcher;
import cn.muses.trade.netty.filter.AccessAuthFilter;
import cn.muses.trade.netty.filter.DelegatingHawkFilterProxy;
import cn.muses.trade.netty.handler.HeartBeatHandler;
import cn.muses.trade.netty.handler.LoginHandler;
import cn.muses.trade.netty.push.HawkPushServiceApi;
import cn.muses.trade.netty.push.impl.HawkPushServiceImpl;
import cn.muses.trade.netty.server.*;
import cn.muses.trade.netty.shiro.HawkShiroFilterFactoryBean;
import cn.muses.trade.netty.shiro.SequenceSessionIdGenerator;
import cn.muses.trade.netty.shiro.cache.SpringCacheManagerWrapper;
import cn.muses.trade.netty.shiro.mgt.DefaultHawkSecurityManager;
import cn.muses.trade.netty.shiro.mgt.DefaultHawkSubjectFactory;
import cn.muses.trade.netty.shiro.realm.HawkServerRealm;
import cn.muses.trade.netty.shiro.session.DefaultHawkSessionManager;
import cn.muses.trade.netty.websocket.WebSocketChannelInitializer;
import cn.muses.trade.service.ChannelEventDealService;
import cn.muses.trade.service.DefaultChannelEventDealService;
import cn.muses.trade.service.DefaultLoginUserService;
import cn.muses.trade.service.LoginUserService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableCaching
public class TradeNettyApplication {

    @Bean
    public NettyProperties nettyProperties(){
        return new NettyProperties();
    }

    @Bean
    public HawkServerRealm hawkServerRealm() {
        return new HawkServerRealm();
    }

    @Bean
    public SequenceSessionIdGenerator sessionIdGenerator() {
        return new SequenceSessionIdGenerator();
    }

    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(SequenceSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    @Bean
    public QuartzSessionValidationScheduler sessionValidationScheduler() {
        QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
        sessionValidationScheduler.setSessionValidationInterval(1800000);
        return sessionValidationScheduler;
    }

    @Bean
    public DefaultHawkSessionManager sessionManager(CachingSessionDAO sessionDAO) {
        DefaultHawkSessionManager defaultSessionManager = new DefaultHawkSessionManager();
        defaultSessionManager.setGlobalSessionTimeout(1800000);
        defaultSessionManager.setDeleteInvalidSessions(true);
//		defaultSessionManager.setSessionValidationSchedulerEnabled(true);
//		defaultSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
        defaultSessionManager.setSessionDAO(sessionDAO);
        return defaultSessionManager;
    }

    @Bean
    public DefaultHawkSubjectFactory hawkSubjectFactory() {
        return new DefaultHawkSubjectFactory();
    }

    @Bean
    public DefaultHawkSecurityManager securityManager(HawkServerRealm hawkServerRealm,
                                                      DefaultHawkSessionManager sessionManager, SpringCacheManagerWrapper springCacheManagerWrapper,
                                                      DefaultHawkSubjectFactory hawkSubjectFactory) {
        DefaultHawkSecurityManager securityManager = new DefaultHawkSecurityManager();
        securityManager.setRealm(hawkServerRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(springCacheManagerWrapper);
        securityManager.setSubjectFactory(hawkSubjectFactory);
        return securityManager;
    }

    @Bean
    public SpringCacheManagerWrapper springCacheManagerWrapper(EhCacheCacheManager cacheManager) {
        SpringCacheManagerWrapper spingCacheManager = new SpringCacheManagerWrapper();
        spingCacheManager.setCacheManager(cacheManager);
        return spingCacheManager;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultHawkSecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

    @Bean
    public HawkShiroFilterFactoryBean hawkShiroFilter(DefaultHawkSecurityManager securityManager) {
        HawkShiroFilterFactoryBean hawkShiroFilter = new HawkShiroFilterFactoryBean();
        hawkShiroFilter.setSecurityManager(securityManager);
        return hawkShiroFilter;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     *
     * <p>Title: codec</p>
     * <p>Description: </p>
     * 消息解码方式，默认不解码
     * @return 解码器
     */
    @Bean
    public DefaultCodec codec(){
        return new DefaultCodec();
    }
    /**
     *
     * <p>Title: dispatcher</p>
     * <p>Description: </p>
     * 请求分发服务
     * @return 分发器
     */
    @Bean
    public HawkRequestDispatcher dispatcher(){
        return  new HawkRequestDispatcher();
    }

    /**
     *
     * @param nettyProperties netty配置
     * @return 请求线程分发程序
     */
    @Bean
    public HandlerThreadDispatcher threadDispatcher(NettyProperties nettyProperties){
        return new HandlerThreadDispatcher(nettyProperties);
    }
    /**
     *
     * <p>Title: hawkContext</p>
     * <p>Description: </p>
     * netty请求处理注解上下文
     * @return 上下文对象
     */
    @Bean
    public HawkContext hawkContext(){
        return new HawkContext();
    }
    /**
     *
     * <p>Title: hawkServerRealHandler</p>
     * <p>Description: </p>
     * netty处理器handler
     * @return netty真实处理句柄
     */
    @Bean
    @ConditionalOnMissingBean(HawkServerHandler.class)
    public HawkServerRealHandler hawkServerRealHandler(){
        return new HawkServerRealHandler();
    }
    /**
     *
     * <p>Title: hawkServerInitializer</p>
     * <p>Description: </p>
     * netty初始化配置
     * @return server 初始化对象
     */
    @Bean
    public ChannelInitializer<SocketChannel> hawkServerInitializer(){
        return new HawkServerInitializer();
    }
    /**
     *
     * <p>Title: hawkServerInitializer</p>
     * <p>Description: </p>
     * netty初始化配置
     * @return server 初始化对象
     */
    @Bean
    public ChannelInitializer<SocketChannel> webSocketChannelInitializer(){
        return new WebSocketChannelInitializer();
    }
    /**
     *
     * <p>Title: nettyServer</p>
     * <p>Description: </p>
     * netty启动程序
     * @return netty服务
     */
    @Bean
    public NettyApplicationStartup nettyApplicationStartup(){
        return new NettyApplicationStartup();
    }
    /**
     *
     * <p>Title: loginUserService</p>
     * <p>Description: </p>
     * 没有登录实体方法的时候，使用默认的方法
     * @return 登陆服务
     */
    @Bean
    @ConditionalOnMissingBean(LoginUserService.class)
    public LoginUserService loginUserService(){
        return new DefaultLoginUserService();
    }


    @Bean
    @ConditionalOnMissingBean(ChannelEventDealService.class)
    public ChannelEventDealService channelEventDealService(){
        return new DefaultChannelEventDealService();
    }
    @Bean
    public NettySpringContextUtils nettySpringContextUtils(){
        return  new NettySpringContextUtils();
    }

    @Bean
    public HawkPushServiceApi hawkPushServiceApi(){
        return new HawkPushServiceImpl();
    }
    @Bean
    public LoginHandler loginHandler(){
        return  new LoginHandler();
    }
    @Bean
    public HeartBeatHandler heartBeatHandler(){
        return  new HeartBeatHandler();
    }
    @Bean
    public AccessAuthFilter accessAuthFilter(){
        return  new AccessAuthFilter();
    }
    @Bean
    public DelegatingHawkFilterProxy delegatingHawkFilterProxy(){
        return new DelegatingHawkFilterProxy();
    }
}

