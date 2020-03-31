package pers.xy.redis.service;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.reactivestreams.Publisher;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Component
public class TestService {
    @Autowired
    Config config;

    public Long test() {
        RedissonClient client = Redisson.create(config);
        RAtomicLong longObject = client.getAtomicLong("myLong");
// 同步执行方式
        longObject.compareAndSet(3, 401);

        return longObject.get();
    }

    public void testReactive(){
        RedissonReactiveClient client = Redisson.createReactive(config);
        RAtomicLongReactive longObject = client.getAtomicLong("myLong");
        Mono<Boolean> result = longObject.compareAndSet(3, 401);
        Publisher<Long> publisher = longObject.get();

    }

    public Long testAsync() throws InterruptedException {
        RedissonClient client = Redisson.create(config);
        RAtomicLong longObject = client.getAtomicLong("myLong");
        // 异步执行方式
        RFuture<Boolean> result = longObject.compareAndSetAsync(3, 401);
        result.whenComplete((res, exception) ->{

        });

        return longObject.get();
    }

    public void testAsyncStream(){
        // 异步流执行方式
        RedissonRxClient client = Redisson.createRx(config);
        RAtomicLongRx longObject= client.getAtomicLong("myLong");
// RxJava2方式
        Single<Boolean> result = longObject.compareAndSet(3, 401);
    }
}
