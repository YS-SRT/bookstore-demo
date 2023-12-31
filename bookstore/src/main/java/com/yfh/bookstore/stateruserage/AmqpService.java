// package com.yfh.bookstore.stateruserage;

// import java.io.IOException;

// import com.rabbitmq.client.Channel;
// import com.yfh.bookstore.config.Setting;

// import org.springframework.stereotype.Service;

// import cn.hutool.core.date.DateUtil;
// import lombok.extern.slf4j.Slf4j;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.rabbit.annotation.EnableRabbit;
// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.amqp.rabbit.connection.CorrelationData;
// import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

// @Slf4j
// @EnableRabbit
// @Service
// public class AmqpService implements ConfirmCallback {

//     @Override
//     public void confirm(CorrelationData arg0, boolean arg1, String arg2) {
//         if(!arg1){
//             log.info("RabbitMQ Sent fail, {}", arg2);
//         }
        
//     }

//     @RabbitListener(queues = Setting.RABBITMQ_DELAY_QUEUE)
//     public void process(String msg, Channel channel, Message message) {
//         log.info("{},Received delay message: <msg:{}>", DateUtil.date(), msg);
//         try {
//             channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
    
// }
