package com.tian.service;

import com.tian.dto.DemoDto;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月15日 16:52
 */
@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    @Override
    public DemoDto test() {
        // TODO: 2022/11/15 集成 MyBatis 操作数据库
        DemoDto demoDto = new DemoDto();
        demoDto.setId(111);
        demoDto.setName("田哥");
        return demoDto;
    }
}
