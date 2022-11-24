package com.tian.config;

import com.tian.entity.PatientSystemMessage;
import com.tian.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月24日 09:08
 */
@Component
public class MsgSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 支付成功  站内信通知
     *
     * 记住：这边传过发到MQ的 message 要和hospital-mq 中的listener接收参数 对应起来哈。
     */
    public void sendPaySuccessMsg(PatientSystemMessage message) {
        rabbitTemplate.convertAndSend(QueueEnum.PAY_SUCCESS_MSG.getExchange(), QueueEnum.PAY_SUCCESS_MSG.getRouteKey(), message);
    }
}
