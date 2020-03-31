package pers.xy.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.xy.common.EnableCommon;

@SpringBootApplication
@EnableCommon
public class ZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkApplication.class, args);
    }

}
