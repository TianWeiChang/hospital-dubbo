package com.tian.listener;

import com.tian.entity.PatientSystemMessage;
import com.tian.service.PatientSystemMessageService;
import com.tian.util.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月24日 08:38
 * <p>
 * 病人站内信
 * 支付完成---》MQ----》PatientSystemMessageListener
 */
@Slf4j
@Component
public class PaySuccessMsgListener {

    @Reference(version = "1.0.0")
    private PatientSystemMessageService patientSystemMessageService;

    @RabbitHandler
    @RabbitListener(queues = MqConstants.PAY_SUCCESS_MSG)
    public void paySuccessMsg(PatientSystemMessage patientSystemMessage) {
        /**
         *  支付成功后，站内信 入库
         */
        log.info("支付成功，站内信入库 : {}", patientSystemMessage);
        patientSystemMessageService.insert(patientSystemMessage);
    }
}
