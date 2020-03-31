package pers.xy.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    Config redissonClient(){
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://172.0.13.37:7000", "redis://172.0.13.37:7001")
                .addNodeAddress("redis://172.0.13.37:7002")
                .addNodeAddress("redis://172.0.13.37:7003")
                .addNodeAddress("redis://172.0.13.37:7004")
                .addNodeAddress("redis://172.0.13.37:7005");

        return config;
    }



}
