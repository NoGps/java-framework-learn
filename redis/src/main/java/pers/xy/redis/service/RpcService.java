package pers.xy.redis.service;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RpcService {

    @Autowired
    Config config;

    public void publishService(){
        RedissonClient client = Redisson.create(config);
        RRemoteService remoteService = client.getRemoteService();
        RpcServiceImpl rpcService = new RpcServiceImpl();
        remoteService.register(RpcServiceInterface.class, rpcService);

        // 注册了12个服务端工作者实例，可以同时执行12个并发调用
        //remoteService.register(SomeServiceInterface.class, someServiceImpl, 12);
    }

    public void callService(){
        RedissonClient client = Redisson.create(config);
        RRemoteService remoteService = client.getRemoteService();
        RpcServiceInterface service = remoteService.get(RpcServiceInterface.class);

        service.exec("hello, rpc!");
    }
}
