package com.tian.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月13日 16:10
 * <p>
 * 查询支付单状态
 * <p>
 * Configuration注解 1.主要用于标记配置类，兼备Component的效果。
 * EnableScheduling 注解 2.开启定时任务
 */
@Slf4j
@Configuration
@EnableScheduling
public class PayOrderQueryTask {


    //添加定时任务
    @Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：30秒
    //@Scheduled(fixedRate=30000)
    private void configureTasks() {
     /*   log.info("定时任务 查询支付单状态: start");
        outerPatientRegisterService.payOrderQuery();
        log.info("定时任务  查询支付单状态: end");*/
    }
}
