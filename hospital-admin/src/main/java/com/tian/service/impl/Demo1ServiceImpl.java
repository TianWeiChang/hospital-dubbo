package com.tian.service.impl;

import com.tian.dto.SystemDto;
import com.tian.service.Demo1Service;
import com.tian.service.DemoService;
import com.tian.service.SystemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月15日 16:35
 * <p>
 * demo案例
 */
@Service
public class Demo1ServiceImpl implements Demo1Service {

    @Reference(version = "1.0.0")
    private DemoService demoService;

    @Reference(version = "1.0.0")
    private SystemService systemService;

    @Override
    public SystemDto test() {
        return systemService.test();
    }
}
