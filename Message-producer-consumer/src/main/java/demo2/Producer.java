package demo2;

import com.rabbitmq.client.Channel;
import utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author zg
 */
public class Producer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME="hello";
    /**
     * 发送大量的消息
     */
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        /*
          生成队列 1. 队列名称
          2. 队列中的消息是否持久化 默认消息存储在内存中
          3. 队列是否只能供一个消费者消费 是否共享 true 可以被多个消费者消费
          4.是否自动删除最后一条消息 消费者打开连接后是否自动删除队列 true 自动删除
          5.其他参数
          */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //从控制台当中发送信息
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String message=scanner.next();
             /*
              发送消息
              1.发送到哪个交换机
              2.哪个是路由键
              3.其他参数信息
              4.发送消息的消息体
             */

            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(message+" sent");
        }
    }

}
