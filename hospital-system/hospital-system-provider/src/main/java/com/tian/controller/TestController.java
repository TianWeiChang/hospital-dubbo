package com.tian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月18日 22:18
 */
@RestController
public class TestController {


    @GetMapping("/test")
    public Object test() {
        return null;
    }
}
