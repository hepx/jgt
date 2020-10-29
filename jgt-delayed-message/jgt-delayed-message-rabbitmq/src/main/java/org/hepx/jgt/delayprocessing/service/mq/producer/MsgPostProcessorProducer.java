package org.hepx.jgt.delayprocessing.service.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.delayprocessing.config.MqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息后置处理，采用MQ的延时消费能力来解决某一操作后指定生效时间执行
 * 说明：发送一条消息到DELAYED_QUEUE，指定延期消费的时间（毫秒）=指定生效时间-当前系统时间
 * 监听DELAYED_QUEUE，然后进行后置处理
 *
 * @author hepx
 * @date 2020/9/21 17:20
 */
@Component
@Slf4j
public class MsgPostProcessorProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param msg
     * @param expiration 延时时长（毫秒）
     */
    public void sendMsg(String msg, long expiration) {
        log.info("MsgPostProcessorProducer.sedMsg:{},{}", msg, expiration);
        rabbitTemplate.convertAndSend(MqConfig.DELAYED_EXCHANGE, MqConfig.DELAYED_QUEUE, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", expiration);
                return message;
            }
        });
    }
}
