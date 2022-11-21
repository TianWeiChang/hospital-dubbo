package com.tian;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月20日 16:08
 */
@EnableDubbo //开启dubbo的注解支持
@SpringBootApplication
public class OutpatientApplication {
    public static void main(String[] args) {
        SpringApplication.run(OutpatientApplication.class, args);
    }
}
