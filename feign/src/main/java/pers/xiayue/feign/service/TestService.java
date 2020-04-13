package pers.xiayue.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pers.xiayue.feign.config.OAuth2FeignAutoConfiguration;

import java.util.List;

@FeignClient(value = "feign", url = "172.0.13.37", configuration = OAuth2FeignAutoConfiguration.class)
public interface TestService {
    @GetMapping("/auth/v1/users/api/getNameList")
    List<String> test ();
}
