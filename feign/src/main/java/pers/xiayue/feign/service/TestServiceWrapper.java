package pers.xiayue.feign.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceWrapper {
    @Resource
    TestService testService;
    public void test(){
        testService.test();
    }
}
