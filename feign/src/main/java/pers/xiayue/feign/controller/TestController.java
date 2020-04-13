package pers.xiayue.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiayue.feign.service.TestService;
import pers.xiayue.feign.service.TestServiceWrapper;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping
    public void test(){
        testService.test();
    }
}
