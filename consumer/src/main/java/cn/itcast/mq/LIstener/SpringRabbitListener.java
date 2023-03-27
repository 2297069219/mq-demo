package cn.itcast.mq.LIstener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * ClassName: SpringRabbitListener
 * Package: cn.itcast.mq.helloworld.LIstener
 * Description:
 *
 * @Author: GuanYuJie
 * @Create:2023/3/27 - 14:41
 * Version: v1.0
 */
@Component
public class SpringRabbitListener {
/*
docker run \
 -e RABBITMQ_DEFAULT_USER=itcast \
 -e RABBITMQ_DEFAULT_PASS=123321 \
 --name mq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management
 */
//    @RabbitListener(queues="simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消息simple。queue是【"+msg+"】消息");
//
//    }
    @RabbitListener(queues="simple.queue")
    public void listenWorkQueue(String msg) throws InterruptedException {
        System.out.println("消息simple。queue是【"+msg+"】消息"+ LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues="simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消息...====>>>。queue是【"+msg+"】消息"+LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value=@Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}

    ))
    public void listenDirectQueue1(String msg){
        System.err.println("消息...====>>>。DirectQueue1是【"+msg+"】消息"+LocalTime.now());

    }
    @RabbitListener(bindings = @QueueBinding(
            value=@Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}

    ))
    public void listenDirectQueue2(String msg){
        System.err.println("消息...====>>>。DirectQueue2是【"+msg+"】消息"+LocalTime.now());

    }
    @RabbitListener(bindings=@QueueBinding(
            value=@Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic",type=ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.err.println("消息...====>>>。TopicQueue1是【"+msg+"】消息"+LocalTime.now());

    }
    @RabbitListener(bindings=@QueueBinding(
            value=@Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic",type=ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.err.println("消息...====>>>。TopicQueue2是【"+msg+"】消息"+LocalTime.now());

    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> msg){
        System.out.println("接收到消息【"+msg+"】");

    }

}
