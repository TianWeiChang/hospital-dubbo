package com.tian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 公众号：java后端技术全栈
 * @date 2022-11-24
 *
 *  没有涉及到数据库操作，所以先排除掉数据源DataSourceAutoConfiguration
 *
 *  项目启动顺序：hospital-system -->> hospital-center -->> hospital-outpatient
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }
}
