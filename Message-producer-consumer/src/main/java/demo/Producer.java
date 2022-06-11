package demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @author zg
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.217.128");
        factory.setUsername("admin");
        factory.setPassword("123");

       /*
        channel实现了自动关闭接口，自动关闭，不需要显式关闭
        */
        try (Connection connection = factory.newConnection(); Channel channel =
                connection.createChannel()) {
            /*
              生成队列 1. 队列名称
              2. 队列中的消息是否持久化 默认消息存储在内存中
              3. 队列是否只能供一个消费者消费 是否共享 true 可以被多个消费者消费
              4.是否自动删除最后一条消息 消费者打开连接后是否自动删除队列 true 自动删除
              5.其他参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello world";
            /*
              发送消息
              1.发送到哪个交换机
              2.哪个是路由键
              3.其他参数信息
              4.发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("message sent");
        }
    }
}