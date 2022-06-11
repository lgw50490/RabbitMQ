package demo3;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import utils.RabbitMqUtils;
import utils.SleepUtils;

/**
 * @author zg
 */
public class Consumer2 {
    public static final String QUEUE_NAME = "ack_queue";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2等待接收消息成立时间较长......");



        DeliverCallback deliverCallback=(consumerTag, message)->{
            //睡眠1s
            SleepUtils.sleep(5);
            System.out.println("received message："+new String(message.getBody()));
            //手动应答
            /*
              1.消息的标记
              2.是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);

        };
        //设置不公平分发
        int prefetchCount=1;
        channel.basicQos(prefetchCount);
        //取消消息时执行以下操作
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };
        //采用手动应答
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);

    }
}
