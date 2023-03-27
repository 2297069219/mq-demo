package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SpringAmqpTest
 * Package: cn.itcast.mq.spring
 * Description:
 *
 * @Author: GuanYuJie
 * @Create:2023/3/27 - 14:32
 * Version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello,spring amqp";

        rabbitTemplate.convertAndSend(queueName, message);
    }
    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,spring amqp__";
        for(int i=0;i<50;i++){
            rabbitTemplate.convertAndSend(queueName, message+i);
            Thread.sleep(20);
        }
    }
    @Test
    public void testSendDirectExchange() {
        String exchangeName = "itcast.direct";
        String message = "hello,blue";

        rabbitTemplate.convertAndSend(exchangeName,"blue", message);
    }

    @Test
    public void testSendTopicExchange() {
        String exchangeName = "itcast.topic";
        String message = "hello,okok";

        rabbitTemplate.convertAndSend(exchangeName,"china.news", message);
    }

    @Test
    public void testSendObjectQueue(){
        Map<String,Object> msg=new HashMap<>();
        msg.put("name","ok");
        msg.put("age",18);
        rabbitTemplate.convertAndSend("object.queue",msg);


    }

}
