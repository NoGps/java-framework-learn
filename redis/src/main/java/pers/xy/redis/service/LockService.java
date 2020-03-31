package pers.xy.redis.service;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LockService {
    @Autowired
    Config config;

    public void lock(){
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("myLock");
        lock.lock();
    }

    //java.lang.IllegalMonitorStateException: attempt to unlock lock, not locked by current thread by node id: e6c0783e-c83b-45d8-9f07-840875f229f7 thread-id: 33
    public void unLock(){
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("myLock");
        lock.unlock();
    }
}
