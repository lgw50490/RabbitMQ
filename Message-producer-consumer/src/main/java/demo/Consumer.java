package demo;

import com.rabbitmq.client.*;


/**
 * @author zg
 */
public class Consumer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.217.128");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("waiting to receive a message....");
        //推送消息如何消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        //消费时删除队列等取消消费的回调接口
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("Message consumption is interrupted");
        };
        /*
         消费者消费消息
         1. 消费哪个队列
         2. 消费成功后是否自动响应 true 表示自动响应 false 手动响应
         3. 消费不成功的消费者回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}