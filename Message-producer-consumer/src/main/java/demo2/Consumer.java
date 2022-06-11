package demo2;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import utils.RabbitMqUtils;

/**
 * @author zg
 */
public class Consumer {
    /**
     * 队列的名称
     */
    public static final String QUEUE_NAME = "hello";
    /**
     * 接收消息
     */
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("received message："+new String(message.getBody()));
        };
        //取消消息时执行以下操作
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };
        System.out.println("C2等待接收消息......");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
