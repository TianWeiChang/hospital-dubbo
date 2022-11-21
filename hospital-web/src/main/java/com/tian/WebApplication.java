package com.tian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author tiange
 * @date 2022-11-20
 *
 * 1、菜单直接写死（配置）
 * 2、挂号和挂号记录查询在一个页面，模仿着其他类似页面写个增加和查询
 * 3、消息通知表 站内信（挂号成功、项目检查完毕、支付成功消息、未支付提醒）
 *
 * 依赖服务：SystemApplication、OutpatientApplication
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
