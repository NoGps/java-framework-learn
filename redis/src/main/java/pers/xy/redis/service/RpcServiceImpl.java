package pers.xy.redis.service;

public class RpcServiceImpl implements RpcServiceInterface {
    @Override
    public void exec(String str) {
        System.out.println("str");
    }
}
