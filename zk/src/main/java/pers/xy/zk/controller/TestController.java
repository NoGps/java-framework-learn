package pers.xy.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xy.common.dto.UserDto;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserDto userDto;

    @GetMapping
    public void test(){
        String test = "test";
    }
}
