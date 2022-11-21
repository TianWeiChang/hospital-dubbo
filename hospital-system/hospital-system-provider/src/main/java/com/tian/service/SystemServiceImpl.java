package com.tian.service;

import com.tian.dto.SystemDto;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月15日 17:23
 */
@Service(version = "1.0.0")
public class SystemServiceImpl implements SystemService {
    @Override
    public SystemDto test() {
        // TODO: 2022/11/15 集成 MyBatis 操作数据库
        return new SystemDto(1, "系统管理");
    }
}
