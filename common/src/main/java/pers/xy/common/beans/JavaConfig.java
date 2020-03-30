package pers.xy.common.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xy.common.dto.UserDto;

@Configuration
public class JavaConfig {
    @Bean
    UserDto userDto(){
        return new UserDto();
    }
}
