package org.hepx.jgt.delayedmsg.rabbitmq.service.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.delayedmsg.rabbitmq.config.MqConfig;
import org.hepx.jgt.delayedmsg.rabbitmq.service.mq.producer.MsgPostProcessorProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 后置处理
 * 监听DELAYED_QUEUE 详细：{@link MsgPostProcessorProducer}
 *
 * @author hepx
 * @date 2020/9/21 17:41
 */
@Component
@Slf4j
public class MsgPostProcessor {

    @RabbitListener(queues = MqConfig.DELAYED_QUEUE)
    public void receive(String msg) {
        log.info("接收到的消息:{}", msg);
        /*bus code*/
    }
}
