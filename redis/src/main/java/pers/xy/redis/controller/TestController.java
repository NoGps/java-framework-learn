package pers.xy.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xy.redis.service.LockService;
import pers.xy.redis.service.RpcService;
import pers.xy.redis.service.TestService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @Autowired
    RpcService rpcService;

    @Autowired
    LockService lockService;

    @GetMapping
    public String test() throws Exception {
        Long value = testService.test();

        return "test";
    }

    @GetMapping("/rpcsub")
    public String testSubRpc(){
        rpcService.publishService();

        return "sub rpc";
    }

    @GetMapping("/callrpc")
    public String callRpc(){
        rpcService.callService();

        return "call rpc";
    }

    @RequestMapping("/lock")
    public String lock(){
        lockService.lock();

        return "lock";
    }

    @RequestMapping("/unlock")
    public String unLock(){
        lockService.unLock();

        return "unLock";
    }

}
