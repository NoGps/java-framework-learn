package pers.xy.common;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Import({AutoConfiguration.class})
public @interface EnableCommon {
}
