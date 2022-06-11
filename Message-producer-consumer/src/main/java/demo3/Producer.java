package demo3;

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
    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //从控制台输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
             /*
              发送消息
              1.发送到哪个交换机
              2.哪个是路由键
              3.其他参数信息
              4.发送消息的消息体
             */

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(message + " sent");
        }
    }


}

