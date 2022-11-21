package com.tian.controller;

import com.tian.service.Demo1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月15日 16:35
 */
@RestController
public class TestController {

    @Resource
    private Demo1Service demo1Service;

    @GetMapping("/")
    public Object test() {
        return demo1Service.test();
    }
}
