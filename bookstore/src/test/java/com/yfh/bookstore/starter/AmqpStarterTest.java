// package com.yfh.bookstore.starter;

// import com.yfh.bookstore.config.Setting;

// import org.junit.jupiter.api.Test;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import cn.hutool.core.date.DateUtil;


// @SpringBootTest
// public class AmqpStarterTest {

//     @Autowired
//     private RabbitTemplate rabbitTemplate;

//     @Test
//     public void sendDelayMsg(){

//         rabbitTemplate.convertAndSend(Setting.RABBITMQ_DELAY_EXCHANGE, Setting.RABBITMQ_DELAY_ROUTER, 
//                                      String.format("msg time: %s", DateUtil.date().toString()), 
//                                      message->{message.getMessageProperties().setDelay(6*1000); return message;});
//     }

    
// }
