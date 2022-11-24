package com.tian.config;

import com.tian.enums.QueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @description 直连模式
 * @createTime 2022年10月15日 08:51
 */
@Configuration
public class DirectExchangeConfig {

    @Bean
    public Queue drugInfoOperationLogQueue() {
        return new Queue(QueueEnum.DRUG_INFO_OPERATION_LOG.getQueueName(), true);
    }

    @Bean
    public DirectExchange drugInfoOperationLogDirectExchange() {
        return new DirectExchange(QueueEnum.DRUG_INFO_OPERATION_LOG.getExchange(), true, false);
    }
    @Bean
    public Binding bindingDirectExchange4drugInfoOperationLog(Queue drugInfoOperationLogQueue, DirectExchange drugInfoOperationLogDirectExchange) {
        return BindingBuilder.bind(drugInfoOperationLogQueue).to(drugInfoOperationLogDirectExchange).with(QueueEnum.DRUG_INFO_OPERATION_LOG.getRouteKey());
    }

    @Bean
    public Queue loginLogQueue() {
        return new Queue(QueueEnum.LOGIN_LOG.getQueueName(), true);
    }

    @Bean
    public DirectExchange loginLogDirectExchange() {
        return new DirectExchange(QueueEnum.LOGIN_LOG.getExchange(), true, false);
    }
    @Bean
    public Binding bindingDirectExchange4LoginLog(Queue loginLogQueue, DirectExchange loginLogDirectExchange) {
        return BindingBuilder.bind(loginLogQueue).to(loginLogDirectExchange).with(QueueEnum.LOGIN_LOG.getRouteKey());
    }


    @Bean
    public Queue doctorAddLogQueue() {
        return new Queue(QueueEnum.DOCTOR_ADD_LOG.getQueueName(), true);
    }

    @Bean
    public DirectExchange doctorAddLogDirectExchange() {
        return new DirectExchange(QueueEnum.DOCTOR_ADD_LOG.getExchange(), true, false);
    }
    @Bean
    public Binding bindingDirectExchange4DoctorAddLog(Queue doctorAddLogQueue, DirectExchange doctorAddLogDirectExchange) {
        return BindingBuilder.bind(doctorAddLogQueue).to(doctorAddLogDirectExchange).with(QueueEnum.DOCTOR_ADD_LOG.getRouteKey());
    }

    @Bean
    public Queue prescriptionPricingQueue() {
        return new Queue(QueueEnum.PRICING_LOG.getQueueName(), true);
    }

    @Bean
    public DirectExchange prescriptionPricingAddLogDirectExchange() {
        return new DirectExchange(QueueEnum.PRICING_LOG.getExchange(), true, false);
    }
    @Bean
    public Binding bindingDirectExchange4PrescriptionPricingAddLog(Queue prescriptionPricingQueue, DirectExchange prescriptionPricingAddLogDirectExchange) {
        return BindingBuilder.bind(prescriptionPricingQueue).to(prescriptionPricingAddLogDirectExchange).with(QueueEnum.PRICING_LOG.getRouteKey());
    }


    /**
     * 支付成功站内信
     */
    @Bean
    public Queue paySuccessMsgQueue() {
        return new Queue(QueueEnum.PAY_SUCCESS_MSG.getQueueName(), true);
    }

    @Bean
    public DirectExchange paySuccessMsgDirectExchange() {
        return new DirectExchange(QueueEnum.PAY_SUCCESS_MSG.getExchange(), true, false);
    }
    @Bean
    public Binding bindingDirectExchange4PaySuccessMsg(Queue paySuccessMsgQueue, DirectExchange paySuccessMsgDirectExchange) {
        return BindingBuilder.bind(paySuccessMsgQueue).to(paySuccessMsgDirectExchange).with(QueueEnum.DRUG_INFO_OPERATION_LOG.getRouteKey());
    }
}