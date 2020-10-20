package org.hepx.jgt.delayprocessing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Mq config
 *
 * @author hepx
 * @date 2020/9/19 14:30
 */
@Configuration
public class MqConfig {
    /**
     * 变更通知队列 配置
     */
    public static final String PATH_CHANGE_NOTIC = "pc_q_path_change_notice";

    @Bean
    public Queue buidPathChangeNoticQueue() {
        return new Queue(PATH_CHANGE_NOTIC);
    }

    /**
     * 延时消费队列配置
     * 延时消费功能需要要用到MQ的插件：rabbitmq-delayed-message-exchange
     * 注：要先安装并启用插件
     */
    public static final String DELAYED_EXCHANGE = "pc_x_delayed";
    public static final String DELAYED_QUEUE = "pc_q_delayed_msg";
    public static final String DELAYED_KEY = DELAYED_QUEUE;
    public static final String EXCHANGE_TYPE = "x-delayed-message";

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE, EXCHANGE_TYPE, true, false, args);
    }

    @Bean
    public Queue buidDelayQueue() {
        return new Queue(DELAYED_QUEUE, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(buidDelayQueue()).to(delayExchange()).with(DELAYED_KEY).noargs();
    }

}
