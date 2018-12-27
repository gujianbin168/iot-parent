package com.chint.datapool.cloud.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import com.chint.datapool.cloud.common.util.RedisUtil;
import com.chint.datapool.cloud.service.IRedisService;
import com.chint.datapool.cloud.service.impl.RedisServiceImpl;
/**
 * redis配置类
 *
 * @author gujianbin
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "redis", name = "host")
public class RedisConfig {

    //Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
    public final static String REDIS_POOL = "jedisPool";
    public final static String REDIS = "redisTemplate";
    public final static String REDIS_SERVICE = "redisService";

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private int index;

    /*
     * 连接池
     */
    @Bean(REDIS_POOL)
    @ConfigurationProperties(prefix = "redis.pool")
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    /*
     * 连接对象
     */
    @Bean(REDIS)
    @ConfigurationProperties(prefix = "redis")
    public RedisTemplate<String, ?> getRedisTemplate(@Qualifier(REDIS_POOL) JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
//        logger.error("***********host:***********"+host);
//        logger.error("***********port:***********"+port);
//        logger.error("***********password:***********"+password);
//        logger.error("***********index:***********"+index);
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(index);
        factory.afterPropertiesSet();
        RedisTemplate<String, ?> template = new StringRedisTemplate(factory);
        template.afterPropertiesSet();
        return template;
    }

    /*
     * 连接服务类
     */
    @Bean(REDIS_SERVICE)
    public IRedisService getRedisService(@Qualifier(REDIS) RedisTemplate<String, ?> redisTemplate) {
        IRedisService redisService = new RedisServiceImpl(redisTemplate);
        RedisUtil.setRedisService(redisService); 
        return redisService;
    }

}