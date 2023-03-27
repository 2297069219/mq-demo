package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FanoutConfig
 * Package: cn.itcast.mq.config
 * Description:
 *
 * @Author: GuanYuJie
 * @Create:2023/3/27 - 15:19
 * Version: v1.0
 */
@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("itcast.fanout");
    }
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanoutQueue1");
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanoutQueue2");
    }

    @Bean
    public Binding fanoutBinding(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }
    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object.queue");
    }

}
