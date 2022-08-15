package com.app.wechat.configuration;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisConfig {
    private static Map<Integer, RedisTemplate<String, Object>> redisTemplateMap = new HashMap<>();
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private long timeout;
    @Value("${spring.redis.jedis.pool.maxActive}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.maxWait}")
    private int maxWait;
    @Value("${spring.redis.jedis.pool.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.minIdle}")
    private int minIdle;
    @Value("${spring.redis.db-num}")
    private List<Integer> dbList;
    private int defaultDB;

    /**
     * 初始化连接池
     */
    @PostConstruct
    public void initRedisTemplate() {
        //设置默认数据库
        defaultDB = dbList.get(0);
        for (Integer db : dbList) {
            //存储多个RedisTemplate实例
            redisTemplateMap.put(db, redisTemplate(db));
        }
    }

    public RedisTemplate<String, Object> redisTemplate(int database) {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setDatabase(database);
        standaloneConfiguration.setHostName(host);
        standaloneConfiguration.setPort(port);
        if (StringUtils.isNotEmpty(password)) {
            RedisPassword redisPassword = RedisPassword.of(password);
            standaloneConfiguration.setPassword(redisPassword);
        }
        GenericObjectPoolConfig<Object> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        LettuceConnectionFactory connectionFactory = lettuceConnectionFactory(standaloneConfiguration, genericObjectPoolConfig, timeout);
        connectionFactory.afterPropertiesSet();
        return createRedisTemplate(connectionFactory);
    }

    private LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration standaloneConfiguration, GenericObjectPoolConfig genericObjectPoolConfig, long timeout) {
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofSeconds(timeout));
        return new LettuceConnectionFactory(standaloneConfiguration, builder.build());
    }

    private RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
/*        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);*/
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(stringRedisSerializer);
        //hash序列化方式采用jackson
        template.setHashValueSerializer(stringRedisSerializer);
/*        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);*/
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 分布式锁注册
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(destroyMethod = "destroy")
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "lock");
    }

    /**
     * 指定数据库进行切换
     *
     * @param db 数据库索引
     * @return
     */
    public RedisTemplate<String, Object> getRedisTemplateByDb(int db) {
        return redisTemplateMap.get(db);
    }

    /**
     * 使用默认数据库
     *
     * @return
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplateMap.get(defaultDB);
    }
}
