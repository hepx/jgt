package org.hepx.jgt.delayedmsg.rabbitmq.service.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.delayedmsg.rabbitmq.config.MqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MSG相关MQ的操作
 *
 * @author hepx
 * @date 2020/9/19 14:50
 */
@Component
@Slf4j
public class MsgProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送路线变更通知
     * @param msg
     */
    public void sendChangeNotice(String msg){
        log.info("sendChangeNotice:{}",msg);
        amqpTemplate.convertAndSend(MqConfig.PATH_CHANGE_NOTIC,msg);
    }

}
